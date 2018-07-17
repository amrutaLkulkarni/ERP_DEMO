/**
 * 
 */
package com.erpsystem.crms.data;

import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ATTRIBUTE_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CURRENT_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EA_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_IDENTIFIER;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.INSERT_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.UPDATE_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_USER_DTLS_BY_MOB;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQUIRY_BY_PROCESS_STATUS;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GENERIC_DATA_SEARCH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import com.erpsystem.crms.model.MasterEntityModel;

/**
 * @author AMRUTA
 *
 */

@Service
public class MasterEntityDaoImpl extends AbstractDatabaseConfig implements IMasterEntityDao {
	
	private static PreparedStatement psmt = null;
	
	private static Connection conn = null;
	
	private int result =  0;
	
	public void addDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
		
		try {
			
			if(null!=masterEntityModel) {
			
			conn = getDbConn();
			psmt = conn.prepareStatement(INSERT_MASTER_ENTITY);
			psmt.setLong(1, masterEntityModel.getEaid());
			psmt.setLong(2, masterEntityModel.getEntityKey());
			psmt.setString(3, masterEntityModel.getValue());
			result = psmt.executeUpdate();
			//conn.commit();
			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	public void searchData(final String entityName,final String searchString) throws Exception {
		
		ResultSet rs = null;
		
		try {
			
			if(null!=entityName && null!=searchString) {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
				psmt.setString(1, searchString);
				psmt.setString(2, entityName);
				psmt.setString(3, searchString);
				psmt.setString(4, entityName);
				rs = psmt.executeQuery();
				
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	
		
	public void updateDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
	
		try {
			
			if (null != masterEntityModel) {

				conn = getDbConn();
				psmt = conn.prepareStatement(UPDATE_MASTER_ENTITY);
				psmt.setLong(1, masterEntityModel.getEaid());
				psmt.setString(2, masterEntityModel.getValue());
				result = psmt.executeUpdate();

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	public long getEntityId(final String entityName) throws Exception {
		
		long entityId = 0;
		String entityidRs = null;
		ResultSet rs = null;
		
		try {
			
			if (null != entityName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENTITY_ID);
				psmt.setString(1, entityName);
				rs = psmt.executeQuery();

			}
			
			while(rs.next()) {
				entityidRs = rs.getString(1);
			}
			
			entityId = Long.parseLong(entityidRs);
			
		} catch(final Exception exception) {
				throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityId;
		
		}
	
	public long getAttributeId(final String attributeName) throws Exception {
		
		long attributeId = 0;
		
		String attributeidRs = null;
		ResultSet rs = null;
		
		try {
			
			if (null != attributeName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ATTRIBUTE_ID);
				psmt.setString(1, attributeName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					attributeidRs = rs.getString(1);
				}
				
				attributeId = Long.parseLong(attributeidRs);

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return attributeId;
		
		}
	
	public long getEAId(final long entityId,final long attributeId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_EA_ID);
				psmt.setLong(2, entityId);
				psmt.setLong(1, attributeId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaidRs = rs.getString(1);
				}
				
				if(null!=eaidRs) {
					eaid = Long.parseLong(eaidRs);
				}else {
					eaid = 0;
				}
				
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
	public long getLatestEntityKeyAndIncrement(final long eaId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_CURRENT_ENTITY_KEY);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaid = rs.getLong(1);
				}
				
				eaid++;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
	
	public long getIdentifier(final long eaId) throws Exception {
		
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_IDENTIFIER);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityKey = rs.getLong(1);
				}
		
				//entityKey = Long.parseLong(entityKeyRs);
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityKey+1;
		
		}
	
	
	public String[] getUserDetailsByMobileNumber(final String mobNo) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_USER_DTLS_BY_MOB);
				psmt.setString(1, mobNo);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}

public String[] getInquiryByProcessStatus(final String processStatus) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENQUIRY_BY_PROCESS_STATUS);
				psmt.setString(1, processStatus);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}
	
}

