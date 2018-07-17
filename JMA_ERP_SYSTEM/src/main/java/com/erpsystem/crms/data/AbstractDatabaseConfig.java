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

/**
 * @author AMRUTA
 *
 */

@Repository
public class AbstractDatabaseConfig {
	
	private static Connection conn = null;
	
	protected static Connection getDbConn() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_system","root","root");
		return conn;

	}
	
	protected static void closeResources(final Connection conn,final ResultSet rs,final PreparedStatement psmt
			,final Statement stmt) throws SQLException {

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
