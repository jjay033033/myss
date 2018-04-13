package top.lmoon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import top.lmoon.util.ExceptionUtil;

public class BaseDAO {
	
	public static String username = System.getenv("DB_USERNAME");
	public static String password = System.getenv("DB_PASSWORD");
	public static String database = System.getenv("DB_DATABASE");
	public static String url = "jdbc:mysql://localhost:3306/%s?user=%s&password=%s&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";

	static {
		// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
		// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = String.format(url, database, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 动态加载mysql驱动
	}

	// private static final Logger logger =
	// LoggerFactory.getLogger(BaseDAO.class);

	private static DataSource dataSource;

	private DataSource lookupDataSource() {
		try {
			Context initialContext = new InitialContext();
			try {
				return (DataSource) initialContext.lookup(System.getenv("DB_JNDI"));
			} catch (NameNotFoundException e) {
				Context envContext = (Context) initialContext.lookup("java:comp/env"); // Tomcat
																						// places
																						// datasources
																						// inside
																						// java:comp/env
				return (DataSource) envContext.lookup(System.getenv("DB_JNDI"));
			}
		} catch (NamingException e) {
			// throw new DataAccessException("Could not look up datasource", e);
			// logger.error("Could not look up datasource:",e);
			System.out.println(ExceptionUtil.getExceptionMessage(e));
			return null;
		}
	}

	private DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = lookupDataSource();
		}
		return dataSource;
	}

//	protected Connection getConnection() throws SQLException {
//		return getDataSource().getConnection();
//	}

	protected Connection getConnection(){
		
		Connection conn = null;
		try {

			// or:
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// or：
			// new com.mysql.jdbc.Driver();

			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			// Statement stmt = conn.createStatement();
			// sql = "create table student(NO char(20),name varchar(20),primary
			// key(NO))";
			// int result = stmt.executeUpdate(sql);//
			// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			// if (result != -1) {
			// System.out.println("创建数据表成功");
			// sql = "insert into student(NO,name) values('2012001','陶伟基')";
			// result = stmt.executeUpdate(sql);
			// sql = "insert into student(NO,name) values('2012002','周小俊')";
			// result = stmt.executeUpdate(sql);
			// sql = "select * from student";
			// ResultSet rs = stmt.executeQuery(sql);//
			// executeQuery会返回结果的集合，否则返回空值
			// System.out.println("学号\t姓名");
			// while (rs.next()) {
			// System.out.println(rs.getString(1) + "\t" + rs.getString(2));//
			// 入如果返回的是int类型可以用getInt()
			// }
			// }
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		// System.out.println(test());
	}

}
