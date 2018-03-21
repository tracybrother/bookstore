package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
}
