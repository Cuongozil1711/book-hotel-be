package main.service;

import main.entity.User;
import main.entity.UserCodeVerify;

import java.util.List;

public interface UserService {
	User getUserInfoByName(String username);

	List<User> getListUser();

	public User findUserByPhone(String phone);
	public User findById(Integer id);
	public User findUserByUid(String uid);

	public void updateUser(User user);

	public User registerUser(User user);

	public UserCodeVerify generateOtp(User id, String phone);

	public User checkSmsUser(String sms, Integer uid);
}
