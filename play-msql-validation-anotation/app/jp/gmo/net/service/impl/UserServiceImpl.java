package jp.gmo.net.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import jp.gmo.net.dto.RegisterFormData;
import jp.gmo.net.models.User;
import jp.gmo.net.models.UserDetail;
import jp.gmo.net.service.UserService;
import play.db.jpa.JPAApi;

public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	public UserServiceImpl() {
	}

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

	public void insertUser(RegisterFormData registerForm) {
		jpaApi
		.withTransaction(() -> {
			try {
				UserDetail userDetail = new UserDetail();
				userDetail.setEmail(registerForm.getEmail());
				userDetail.setUsername(registerForm.getUsername());;
				userDetail.setPassword(registerForm.getPassword());
				userDetail.setAddress(registerForm.getAddress());
				userDetail.setPhone(registerForm.getPhone());
				userDetail.setAge(registerForm.getAge());
				jpaApi.em().persist(userDetail);
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

	@Override
	public UserDetail getUserDetail(String email, String password) {
		return jpaApi
				.withTransaction(() -> {
					EntityManager em = jpaApi.em();

					String sql = "Select u from User u where u.email = :email and u.password = :password";
					Query query = em.createQuery(sql);
					query.setParameter("email", email);
					query.setParameter("password", password);
					UserDetail userDetail = null;
					List<UserDetail> userlist = (List<UserDetail>) query.getResultList();
					if (userlist.size() > 0) {
						userDetail = userlist.get(0);
					}
					return userDetail;
				});
	}

}
