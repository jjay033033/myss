package top.lmoon.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import top.lmoon.util.ExceptionUtil;

/**
 * 
 * @author LMoon
 * @date 2018年4月14日
 *
 */
public class MysqlConnectionPool {

	private static ComboPooledDataSource comboPooledDataSource;

	private MysqlConnectionPool() {
	}

	static {
		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();System.out.println(ExceptionUtil.getExceptionMessage(e));
		// }
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		String database = System.getenv("DB_DATABASE");
		String dbip = System.getenv("DB_IP");
		String url = "jdbc:mysql://%s:3306/%s?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
		comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			System.out.println(ExceptionUtil.getExceptionMessage(e));
		}
		url = String.format(url, dbip, database);
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(username);
		comboPooledDataSource.setPassword(password);
		// 下面是设置连接池的一配置
		comboPooledDataSource.setMaxPoolSize(20);
		comboPooledDataSource.setMinPoolSize(3);
		// fixed mysql connection bug: The last packet sent successfully to the server
		// was 20,820,002 milliseconds ago. is longer than the server configured value
		// of 'wait_timeout'.
		comboPooledDataSource.setTestConnectionOnCheckin(false);
		comboPooledDataSource.setTestConnectionOnCheckout(true);

	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = comboPooledDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(ExceptionUtil.getExceptionMessage(e));
		}
		return connection;
	}

}
