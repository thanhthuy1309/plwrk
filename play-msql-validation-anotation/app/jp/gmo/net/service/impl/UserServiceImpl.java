package jp.gmo.net.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jp.gmo.net.models.User;
import jp.gmo.net.service.UserService;
import play.db.jpa.JPAApi;

public class UserServiceImpl implements UserService {

	private JPAApi jpaApi;

	@Inject
	public UserServiceImpl(JPAApi api) {
		this.jpaApi = api;
	}

	public User getUserInfo(String email, String password) {

		return jpaApi
				.withTransaction(() -> {
					EntityManager em = jpaApi.em();

					String sql = "Select u from User u where u.email = :email and u.password = :password";
					Query query = em.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("password", password);
					User user = null;
					List<User> userlist = (List<User>) query.getResultList();
					if (userlist.size() > 0) {
						user = userlist.get(0);
					}
					return user;
				});
	}

	public void insertUser() {
		jpaApi
		.withTransaction(() -> {
			try {
				for (int i = 3; i < 10; i++) {
					User user = new User();
					user.setEmail("Email" + i);
					user.setName("Name" + i);
					user.setPassword("Password" + i);
//					if (i == 8) {
//						String bn = "dd";
//						bn.charAt(10);
//					}
					jpaApi.em().persist(user);
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				// chi su dung setRollbackOnly() khi rollback cho method insertUser
				jpaApi.em().getTransaction().setRollbackOnly();
				
				// khong su dung rollback => se bao loi
				// jpaApi.em().getTransaction().rollback();
			}
		});
		// kham khao source github
		// https://github.com/playframework/playframework/blob/master/framework/src/play-java-jpa/src/test/java/play/db/jpa/JPATest.java
	}

}
