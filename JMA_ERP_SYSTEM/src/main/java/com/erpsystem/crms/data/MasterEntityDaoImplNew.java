package com.erpsystem.crms.data;

import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ALL_RECORD;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ATTR_COUNT;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CURRENT_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EAID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EAID_UPADTE;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID_FROM_ENTITYNAME;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_FROM_EAID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_IDENTIFIER;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Record_FROM_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.INSERT_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.UPDATE_MASTER_ENTITY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.erpsystem.crms.model.MasterEntityModel;

@Service
public class MasterEntityDaoImplNew extends AbstractDatabaseConfig implements IMasterEntityDaoNew {

	private static PreparedStatement psmt = null;

	private static Connection conn = null;

	private int result = 0;

	public long getEaId(final String ent_name, final String attr_name) throws Exception {

		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_EAID);
			psmt.setString(1, ent_name);
			psmt.setString(2, attr_name);

			rs = psmt.executeQuery();

			while (rs.next()) {
				eaidRs = rs.getString(1);
			}

			if (null != eaidRs) {
				eaid = Long.parseLong(eaidRs);
			} else {
				eaid = 0;
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	public long getLatestEntityKeyAndIncrement(final String ent_name) throws Exception {

		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_CURRENT_ENTITY_KEY);
			psmt.setString(1, ent_name);
			rs = psmt.executeQuery();

			while (rs.next()) {
				eaid = rs.getLong(1);
			}

			

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	
	public void addDataInMasterEntity(MasterEntityModel masterEntityModel) throws Exception {
		// TODO Auto-generated method stub
		try {
			if(null != masterEntityModel) {
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

	
	public long getIdentifier(String ent_name) throws Exception {
		// TODO Auto-generated method stub
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_IDENTIFIER);
				psmt.setString(1, ent_name);
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
		
		return entityKey;
	}

	
	public long getEaidUpdate(String ent_name, String attr_name) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_EAID_UPADTE);
			psmt.setString(1, ent_name);
			psmt.setString(2, attr_name);

			rs = psmt.executeQuery();

			while (rs.next()) {
				eaidRs = rs.getString(1);
			}

			if (null != eaidRs) {
				eaid = Long.parseLong(eaidRs);
			} else {
				eaid = 0;
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaid;

	}

	
	public long getEntityKeyFromEaid(long eaid) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long eaId = 0;
		ResultSet rs = null;

		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ENTITY_KEY_FROM_EAID);
			psmt.setLong(1, eaid);
			rs = psmt.executeQuery();

			while (rs.next()) {
				eaId = rs.getLong(1);
			}

			

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return eaId;

	}

	
	public void updateDataInMasterEntity(MasterEntityModel masterEntityModel) throws Exception {
		// TODO Auto-generated method stub
       try {
			
			if (null != masterEntityModel) {

				conn = getDbConn();
				psmt = conn.prepareStatement(UPDATE_MASTER_ENTITY);
							
				psmt.setString(1, masterEntityModel.getValue());
				psmt.setLong(2, masterEntityModel.getEaid());
				psmt.setLong(3, masterEntityModel.getEntityKey());
				result = psmt.executeUpdate();

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,null,psmt,null);
			}
	
	}

	public List<Map<String,String>> getAllPerson(long entityId) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
				
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ALL_RECORD);
			psmt.setLong(1,entityId);
			rs = psmt.executeQuery();
			
			Map<String,String> recordMap = new HashMap<>(); 
			List<Map<String,String>> jsonMapList = new ArrayList<>();
			
			int counter = 0;
			
			long attrCount = getAttrCount(entityId);
			
			while(rs.next()) {
			
				if(counter <=attrCount-2) {
					
					recordMap.put(rs.getString(4),rs.getString(5));
					counter++;
					
					if(counter==attrCount-1) {
						recordMap.put("entity_key_id", rs.getString(2));
						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}
					
				} /*else {
					counter = 0;
					jsonMapList.add(recordMap);
					recordMap.clear();
					recordMap.put(rs.getString(4),rs.getString(5));
					counter++;
				}*/
				
			}	
			System.out.println(jsonMapList);
		return jsonMapList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}

	}
	
	public long getAttrCount(final long entityId) throws Exception {
		
		ResultSet rs = null;
		long attrCount = 0;
		
		try {
			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ATTR_COUNT);
			psmt.setLong(1,entityId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				attrCount = rs.getLong(1);
			}
			
		}  catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}

		
		
		return attrCount;
	}

	
	public long getEntityIdFromEntityName(String entityName) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_ENTITY_ID_FROM_ENTITYNAME);
			psmt.setString(1, entityName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				entity_id = rs.getLong(1);
			}

			

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return entity_id;
	}

	
	public JSONObject getEntityById(long entity_key, String entity_name) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;
		String attr_name = null;
		String value = null;
		
		JSONObject jsonObject= new JSONObject();
		
		try {

			conn = getDbConn();
			psmt = conn.prepareStatement(GET_Record_FROM_ENTITY_KEY);
			psmt.setLong(1, entity_key);
			psmt.setString(2, entity_name);
			rs = psmt.executeQuery();

			while (rs.next()) {
				attr_name = rs.getString(1);
				value =rs.getString(2);
				jsonObject.put(attr_name, value);
			}


		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;

	}
	
}
