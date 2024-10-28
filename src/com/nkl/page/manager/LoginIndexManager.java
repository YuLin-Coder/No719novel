package com.nkl.page.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.StringUtil;
import com.nkl.page.dao.IUserDao;
import com.nkl.page.domain.User;

@Service
public class LoginIndexManager {

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
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		User _user = userDao.getUser(user);
		
		
		return _user;
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		userDao.updateUserMoney(user);
		
	}
	
	/**
	 * @Title: addUser
	 * @Description: 用户注册
	 * @return void
	 */
	public void addUser(User user) {
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			//user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		user.setUser_money(1000);//默认1000元账户余额
		user.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		user.setUser_kind(1);//普通用户
		user.setUser_type(1);//普通用户
		//默认头像
		if (StringUtil.isEmptyString(user.getUser_photo())) {
			user.setUser_photo("default.jpg");
		}
		userDao.addUser(user);
		
		
	}  

}
