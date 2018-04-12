/**
 * 
 */
package top.lmoon.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.lmoon.jdbc.JdbcTemplate;
import top.lmoon.jdbc.RowMapper;
import top.lmoon.vo.VipVideoVO;

/**
 * @author LMoon
 * @date 2017年8月3日
 * 
 */
public class VipVideoDAO extends BaseDAO{

	private static final Logger logger = LoggerFactory.getLogger(VipVideoDAO.class);

//	private DataSource dataSource;

	public List<VipVideoVO> select(int pageNo,int pageSize) {
		try {
			String sql = "SELECT * FROM vipvideo order by ctime desc limit ? offset ?";
			return JdbcTemplate.queryForList(getConnection(), sql, new RowMapper<VipVideoVO>() {

				@Override
				public VipVideoVO mapRow(ResultSet rs, int rowIndex) throws SQLException {
					VipVideoVO vo = new VipVideoVO();
					Timestamp ctime = rs.getTimestamp("ctime");
					vo.setCtime(new Timestamp(ctime.getTime()+8L*60*60*1000).toString());
					vo.setRemark(rs.getString("remark"));
					return vo;
				}
			}, new Object[]{pageSize,(pageNo-1)*pageSize});
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}
	
	public int selectCount() {
		try {
			String sql = "SELECT count(1) FROM vipvideo";
			return JdbcTemplate.queryForInt(getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public int insert(String remark) {
		try {
			createTable();
			String sql = "insert into vipvideo(remark,ctime) values (?,now())";
			return JdbcTemplate.executeUpdate(getConnection(), sql, new Object[] { remark });
		} catch (Exception e) {
			System.err.println(e);
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}
	
	public int dropTable(){
		try {
			String sql = "drop table vipvideo";
			return JdbcTemplate.executeUpdate(getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			System.err.println(e);
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}
	
	public int createTable(){
		try {
			String sql = "CREATE TABLE IF NOT EXISTS vipvideo (remark text,ctime timestamp)";
			return JdbcTemplate.executeUpdate(getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			System.err.println(e);
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}
		
	public int alterTable(){
		try {
			//postgresql
			String sql = "alter table vipvideo alter column remark TYPE text";
			return JdbcTemplate.executeUpdate(getConnection(), sql, new Object[0]);
		} catch (Exception e) {
			System.err.println(e);
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

}
