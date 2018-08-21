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
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_FROM_ENTITY_KEY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.erpsystem.crms.model.MasterEntityModel;

import javax.sql.DataSource;

@Service
public class MasterEntityDaoImplNew extends AbstractDatabaseConfig implements IMasterEntityDaoNew {

	private PreparedStatement psmt;

	private static Connection conn = null;

	private int result = 0;

	@Autowired
    DataSource dataSource;

	public long getEaId(final String ent_name, final String attr_name) throws Exception {

		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_EAID)){

			//conn = getDbConn();
			//conn = dataSource.getConnection();

			//psmt = connn.prepareStatement(GET_EAID);
			psmtt.setString(1, ent_name);
			psmtt.setString(2, attr_name);

			rs = psmtt.executeQuery();

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

		try (Connection connn = dataSource.getConnection()){

			//conn = getDbConn();
			psmt = connn.prepareStatement(GET_CURRENT_ENTITY_KEY);
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
		try (Connection connn = dataSource.getConnection()){
			if (null != masterEntityModel) {
				//conn = getDbConn();
				psmt = connn.prepareStatement(INSERT_MASTER_ENTITY);
				psmt.setLong(1, masterEntityModel.getEaid());
				psmt.setLong(2, masterEntityModel.getEntityKey());
				psmt.setString(3, masterEntityModel.getValue());
				result = psmt.executeUpdate();
				// conn.commit();
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}
	}

	public long getIdentifier(String ent_name) throws Exception {
		// TODO Auto-generated method stub
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection()){

			//conn = getDbConn();
			psmt = connn.prepareStatement(GET_IDENTIFIER);
			psmt.setString(1, ent_name);
			rs = psmt.executeQuery();

			while (rs.next()) {
				entityKey = rs.getLong(1);
			}

			// entityKey = Long.parseLong(entityKeyRs);

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

		return entityKey;
	}

	public long getEaidUpdate(String ent_name, String attr_name) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_EAID_UPADTE)){

			//conn = getDbConn();
			//psmt = connn.prepareStatement(GET_EAID_UPADTE);
			psmtt.setString(1, ent_name);
			psmtt.setString(2, attr_name);

			rs = psmtt.executeQuery();

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

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_EAID)){

			//conn = getDbConn();
			//psmtt = connn.prepareStatement(GET_ENTITY_KEY_FROM_EAID);
			psmtt.setLong(1, eaid);
			rs = psmtt.executeQuery();

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
		try (Connection connn = dataSource.getConnection()){

			if (null != masterEntityModel) {

				//conn = getDbConn();
				psmt = connn.prepareStatement(UPDATE_MASTER_ENTITY);

				psmt.setString(1, masterEntityModel.getValue());
				psmt.setLong(2, masterEntityModel.getEaid());
				psmt.setLong(3, masterEntityModel.getEntityKey());

				result = psmt.executeUpdate();

			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

	}

	public List<Map<String, String>> getAllPerson(long entityId) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try (Connection connn = dataSource.getConnection(); 
				
			PreparedStatement psmtt = connn.prepareStatement(GET_ALL_RECORD)){
			//conn = getDbConn();
            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_ALL_RECORD);
			psmtt.setLong(1, entityId);
			rs = psmtt.executeQuery();

			Map<String, String> recordMap = new HashMap<>();
			List<Map<String, String>> jsonMapList = new ArrayList<>();

			int counter = 0;
			long attrCount = getAttrCount(entityId);
			while (rs.next()) {

				if (counter <= attrCount - 2) {

					recordMap.put(rs.getString(4), rs.getString(5));
					counter++;

					if (counter == attrCount - 1) {
						recordMap.put("entity_key_id", rs.getString(2));
						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}
				}
			}
			System.out.println(jsonMapList);
			return jsonMapList;
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

	}

	public List<Map<String, String>> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)
			throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;
		
		
		JSONObject jsonObject= new JSONObject();
		
		try (Connection connn = dataSource.getConnection(); 
				
				PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY)){

            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY);
			psmtt.setString(1, entity_name);
			psmtt.setString(2, attr_name);
			psmtt.setString(3, value);
			psmtt.setString(4, entity_name);
			rs = psmtt.executeQuery();

			Map<String, String> recordMap = new HashMap<>();
			List<Map<String, String>> jsonMapList = new ArrayList<>();

			int counter = 0;

			long attrCount = getAttrCount(entityId);

			while (rs.next()) {

				if (counter <= attrCount - 2) {

					recordMap.put(rs.getString(1), rs.getString(2));
					counter++;
					if (counter == attrCount - 1) {

						counter = 0;
						jsonMapList.add(recordMap);
						recordMap = new HashMap<>();
					}

				}
			}
			return jsonMapList;
		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}

	}

	public long getAttrCount(final long entityId) throws Exception {

		ResultSet rs = null;
		long attrCount = 0;

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_ATTR_COUNT)){
            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_ATTR_COUNT);
			psmtt.setLong(1, entityId);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				attrCount = rs.getLong(1);
			}


		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, null, psmt, null);
		}

		return attrCount;
	}

	public long getEntityIdFromEntityName(String entityName) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_ID_FROM_ENTITYNAME)){

            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_ENTITY_ID_FROM_ENTITYNAME);
			psmtt.setString(1, entityName);
			rs = psmtt.executeQuery();

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

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY)){

            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_Record_FROM_ENTITY_KEY);
			psmtt.setLong(1, entity_key);
			psmtt.setString(2, entity_name);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				attr_name = rs.getString(1);
				value = rs.getString(2);
				jsonObject.put(attr_name, value);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;

	}

	@Override
	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception {
		// TODO Auto-generated method stub
		String eaidRs = null;
		long entity_id = 0;
		ResultSet rs = null;

		JSONObject jsonObject = new JSONObject();

		try (Connection connn = dataSource.getConnection(); PreparedStatement psmtt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY)){

            //conn = dataSource.getConnection();
			//psmt = connn.prepareStatement(GET_ENTITY_FROM_ENTITY_KEY);
			psmtt.setString(1, entity_name);
			psmtt.setString(2, attr_name);
			psmtt.setString(3, value);
			rs = psmtt.executeQuery();

			while (rs.next()) {
				// entity_name = rs.getString(1);
				attr_name = rs.getString(1);
				value = rs.getString(2);
				jsonObject.put(attr_name, value);
			}

		} catch (final Exception exception) {
			throw new Exception(exception);
		} finally {
			closeResources(conn, rs, psmt, null);
		}
		return jsonObject;
	}

	@Override
	public void addDataInMasterEntity(long entityKey, String entityName, Map<String, String> jsonMap) throws Exception {
		
		PreparedStatement statement = null;
		
		try (Connection connn = dataSource.getConnection()){

            //conn = dataSource.getConnection();
		statement = connn.prepareStatement("INSERT INTO master_entity (eaid, entity_key,value) VALUES (?,?,?)");
		
		final int batchSize = 150;
		int count = 0;
		
		for(Map.Entry<String, String> entry : jsonMap.entrySet()) {
			   
			  // if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName") ) {
			   if (null != entityName && !entry.getKey().equalsIgnoreCase("entity_name") ) {
				   
				   MasterEntityModel masterEntityModel = getmasterEntityModelDtlsNew(entityName, entry.getKey(), entry.getValue());

					long eaid = Long.valueOf(masterEntityModel.getEaid());
					
					statement.setLong(1, eaid);
					statement.setLong(2,entityKey);
					statement.setString(3,masterEntityModel.getValue() );
					
					statement.addBatch();
					
			   }
			   
			   if(++count % batchSize == 0) {
				   statement.executeBatch();
				}
				  
			   }
		
		statement.executeBatch();
		
		} catch(Exception exception) {
			throw new Exception(exception);
		} finally {
			if(null!=statement) {
				statement.close();
			}
			
			if(null!=conn) {
				conn.close();
			} 
			
			
		}
		
	}
	
	
	private MasterEntityModel getmasterEntityModelDtlsNew(final String ent_name, final String attr_name,
			final String value) throws Exception {

		// step 1 : find eaid from ent_attr_view where ant_name = ? and attr_name =?;
		final long eaid = getEaId(ent_name, attr_name);

		// step 2 : find max entity_key from erp_view where entity_name;
		final long entityKey = getLatestEntityKeyAndIncrement(ent_name);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setValue(value);

		return masterDetailsModel;
	}

}
