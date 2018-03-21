package service;

import java.sql.SQLException;

import dao.UserDao;
import po.User;
import util.MD5Util;

public class UserService {
	
	private UserDao dao = new UserDao();
	
	/**
	 * 注册的功能实现
	 * @throws SQLException 
	 */
	public void regist(User user) throws SQLException {
		// 判断用户名是否存在 --     调用方法
		String value = dao.findByUsername(user.getUsername());
		if(user.getUsername().length()<1||user.getPassword().length()<1) {
			throw new RuntimeException("用户名或密码不能为空!");
		}
		if(Integer.valueOf(value)==1) {
			throw new RuntimeException("用户名已经存在!");
		}
		user.setPassword(MD5Util.getMD5(user.getPassword())); // MD5 加密
		// 实现注册
		dao.insert(user);
	}

}
