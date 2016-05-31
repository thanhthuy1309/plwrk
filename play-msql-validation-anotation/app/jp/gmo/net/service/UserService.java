package jp.gmo.net.service;

import com.google.inject.ImplementedBy;

import jp.gmo.net.dto.RegisterFormData;
import jp.gmo.net.models.User;
import jp.gmo.net.models.UserDetail;
import jp.gmo.net.service.impl.UserServiceImpl;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	public User getUserInfo(String email, String password);
	public void insertUser(RegisterFormData registerForm);
	public UserDetail getUserDetail(String email, String password);
}
