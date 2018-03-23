package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

public class TestDao extends BaseDao {
	
	/**
	 * 测试查询所有
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectAll() throws SQLException {
		String sql = " SELECT * FROM  `sys_area` LIMIT 0,20 ";
		List<Map<String, Object>> list = super.find(sql, null);
		return list;
	}
	
	public static void main(String[] args) throws SQLException {
		
		TestDao testDao = new TestDao();
		List<Map<String, Object>> selectAll = testDao.selectAll();
		for (Map<String, Object> map : selectAll) {
			System.out.println(map.get("name"));
		}
	}
	
	private static final Random random = new Random(System.currentTimeMillis());
	public static final String TOKENPARAM = "session-token";
	 
	/** 生成一个token */
	public static synchronized String generateToken(HttpSession session) {
	    String s = String.valueOf(random.nextLong());
	    session.setAttribute(TOKENPARAM, s);
	    return s;
	}
}
