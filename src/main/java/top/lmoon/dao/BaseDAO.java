package top.lmoon.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	private static DataSource dataSource;

    private DataSource lookupDataSource() {
        try {
            Context initialContext = new InitialContext();
            try {
                return (DataSource) initialContext.lookup(System.getenv("DB_JNDI"));
            } catch (NameNotFoundException e) {
                Context envContext = (Context) initialContext.lookup("java:comp/env");  // Tomcat places datasources inside java:comp/env
                return (DataSource) envContext.lookup(System.getenv("DB_JNDI"));
            }
        } catch (NamingException e) {
//            throw new DataAccessException("Could not look up datasource", e);
        	logger.error("Could not look up datasource:",e);
        	return null;
        }
    }
    
    private DataSource getDataSource() {
    	if(dataSource==null) {
    		dataSource=lookupDataSource();
    	}
        return dataSource;
    }
    
    protected Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

}
