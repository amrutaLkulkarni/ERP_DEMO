/**
 * 
 */
package com.erpsystem.crms.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;
/**
 * @author AMRUTA
 *
 */

@Repository
public class AbstractDatabaseConfig {
	
	private static Connection conn = null;
	@Autowired
	private Environment env;
	
	protected  Connection getDbConn() throws ClassNotFoundException, SQLException {
		
		String datasource = env.getProperty("spring.datasource.url");
		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		
		Class.forName("com.mysql.jdbc.Driver");
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_system","root","root");
		conn = DriverManager.getConnection(datasource,username,password);

		return conn;

	}
	
	protected static void closeResources( Connection conn, ResultSet rs, PreparedStatement psmt
			, Statement stmt) throws SQLException {

		if(null!=conn) {
			conn.close();
		}
		
		if(null!=rs) {
			rs.close();
		}
		
		if(null!=psmt) {
			psmt.close();
		}
		
	}

}
