package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


public class DBUtil {

	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	// 声明连接池
	private static BasicDataSource ds;

	static {
		// 读取连接参数
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			// 数据库连接参数
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("username");
			String pwd = p.getProperty("password");
			// 连接池参数
			// 创建连接池,并给它设置参数
			ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("找不到数据库连接配置文件", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}


	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException {
		Connection con = getConnection();
		if (con != null)
			con.setAutoCommit(false);
	}

	/**
	 * 从ThreadLocal中释放并且关闭Connection,并结束事务
	 * 
	 * @throws SQLException
	 */
	public static void releaseAndCloseConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit();
			tl.remove();
			con.close();

		}
	}

	/**
	 * 事务回滚
	 * @throws SQLException 
	 */

	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}
	/**
	 * 归还连接: 连接池创建的连接,其close方法不再是关闭连接,而是将连接归还给连接池,连接池会将此连接数据清空并标识为空闲.
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("归还连接失败", e);
			}
		}
	}

}
