package com.nkl.admin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.User;

@Service
public class LoginManager {

	@Autowired
	IUserDao userDao;
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户集合
	 * @param user
	 * @return List<Picnews>
	 */
	public List<User> listUsers(User user){
		
		
		List<User> users = userDao.listUsers(user);
		
		
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 查询用户
	 * @param user
	 * @return User
	 */
	public User getUser(User user){
		
		//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		User _user = userDao.getUser(user);
		
		
		return _user;
	}
	
}
