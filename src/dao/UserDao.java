package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import po.User;

public class UserDao extends BaseDao {

	/**
	 * 通过用户名查询用户个数, 返回String类型的数值
	 * 
	 * @throws SQLException
	 * 
	 */
	public String findByUsername(String username) throws SQLException {
		String sql = "select count(*) count from user where username = ?";
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(username);
		Map<String, Object> map = super.findOne(sql, arrayList);
		String s = String.valueOf(map.get("count"));
		return s;
	}

	public void insert(User user) throws SQLException {
		String sql = "insert into user(username,password,gender,email,telephone,introduce) values(?,?,?,?,?,?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user.getUsername());
		params.add(user.getPassword());
		params.add(user.getGender());
		params.add(user.getEmail());
		params.add(user.getTelephone());
		params.add(user.getIntroduce());
		update(sql, params);
	}
	
	// test
	public static void main(String[] args) throws SQLException {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("lisi");
		user.setPassword("a2334818");
		user.setGender("男");
		user.setEmail("274047925@qq.com");
		user.setIntroduce("测试账号");
		user.setTelephone("15887845678");
		userDao.insert(user);
	}
}
