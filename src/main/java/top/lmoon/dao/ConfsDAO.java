/**
 * 
 */
package top.lmoon.dao;

import top.lmoon.jdbc.JdbcTemplate;
import top.lmoon.jdbc.MysqlConnectionPool;
import top.lmoon.shadowsupdate.vo.ConfWebVO;

/**
 * @author LMoon
 * @date 2017年8月3日
 * 
 */
public class ConfsDAO {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ConfsDAO.class);

	// private DataSource dataSource;

	public String selectConf() {
		try {
			// Statement stmt = connection.createStatement();
			// stmt.executeUpdate("CREATE TABLE IF NOT EXISTS confs (id int NOT
			// NULL,conf text,PRIMARY KEY(id))");
			// ResultSet rs = stmt.executeQuery("SELECT conf FROM confs where
			// id=1");
			// if (rs.next()) {
			// conf = rs.getString("conf");
			// }
			createTable();
			String sql = "SELECT conf FROM confs where id=1";
			return JdbcTemplate.queryForString(MysqlConnectionPool.getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			// logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int createTable() {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS confs (id int NOT NULL,conf text,PRIMARY KEY(id))";
			return JdbcTemplate.executeUpdate(MysqlConnectionPool.getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			System.err.println(e);
			// logger.error("", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int updateConf(ConfWebVO vo) {
		return updateConf(vo.getConf());
	}

	public int updateConf(String conf) {
		try {
			// Statement stmt = connection.createStatement();
			// stmt.executeUpdate("CREATE TABLE IF NOT EXISTS confs (id int NOT
			// NULL,conf text,PRIMARY KEY(id))");
			// stmt.executeUpdate("insert into confs(id,conf) values
			// (1,'"+vo.getConf()+"')");
			// return stmt.executeUpdate("update confs set
			// conf='"+vo.getConf()+"' where id=1");
			// PreparedStatement ps = connection.prepareStatement("insert into
			// confs(id,conf) values (?,?)");
			// ps.setInt(1, 1);
			// ps.setString(2, vo.getConf());
			// int result = ps.executeUpdate();
			// String sql = "insert into confs(id,conf) values (1,?)";
			// JdbcTemplate.executeUpdate(getConnection(), sql, new
			// Object[]{conf});
			String sql = "update confs set conf=? where id=1";
			return JdbcTemplate.executeUpdate(MysqlConnectionPool.getConnection(), sql, new Object[] { conf });
		} catch (Exception e) {
			// logger.error("",e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
