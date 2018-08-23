package com.erpsystem.crms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.erpsystem.crms.data.IMasterEntityDaoNew;
import com.erpsystem.crms.model.MasterEntityModel;

@Service
public class CrmsSvcNewImpl implements ICrmsSvcNew {

	@Autowired
	IMasterEntityDaoNew masterEntityDaoNew;

	/*@Transactional(propagation = Propagation.REQUIRED)
	public void addRecordInSystem(final long entityKey, final String ent_name, final String attr_name,
			final String value) throws Exception {

		if (null != masterEntityDaoNew) {
			MasterEntityModel masterEntityModel = getmasterEntityModelDtlsNew(ent_name, attr_name, value);

			long eaid = Long.valueOf(masterEntityModel.getEaid());

			masterEntityModel.setEaid(eaid);
			masterEntityModel.setEntityKey(entityKey);
			masterEntityModel.setValue(value);

			masterEntityDaoNew.addDataInMasterEntity(masterEntityModel);
		}
	}*/
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRecordInSystem(final long entityKey, final String ent_name, final Map<String,String> jsonMap) 
			throws Exception {

		if (null != masterEntityDaoNew) {

			masterEntityDaoNew.addDataInMasterEntity(entityKey,ent_name,jsonMap);
		}
	}

	public long getEntityKeyFromSystem(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		long entity_key = entityKey+1;
		
		return entity_key;

	}

	public long getEntityKeyFromSystemUpdate(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		return entityKey;

	}

	public long getCount(final String entityName) throws Exception {
		
		long count = masterEntityDaoNew.getCount(entityName);
		
		return count;
		
	}
	private MasterEntityModel getmasterEntityModelDtlsNew(final String ent_name, final String attr_name,
			final String value) throws Exception {

		// step 1 : find eaid from ent_attr_view where ant_name = ? and attr_name =?;
		final long eaid = masterEntityDaoNew.getEaId(ent_name, attr_name);

		// step 2 : find max entity_key from erp_view where entity_name;
		final long entityKey = masterEntityDaoNew.getLatestEntityKeyAndIncrement(ent_name);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setValue(value);

		return masterDetailsModel;
	}

	private MasterEntityModel getMasterEntityModelForUpdate(final String ent_name, final String attr_name,
			final String value, final String entity_key) throws Exception {

		final long eaid = masterEntityDaoNew.getEaidUpdate(ent_name, attr_name);

		//final long entityKey = masterEntityDaoNew.getEntityKeyFromEaid(eaid);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(Long.parseLong(entity_key));
		masterDetailsModel.setValue(value);

		return masterDetailsModel;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRecordInSystem(String entityName, String key, String value, String entity_key) throws Exception {
		// TODO Auto-generated method stub
		if (null != masterEntityDaoNew) {
			MasterEntityModel masterEntityModel = getMasterEntityModelForUpdate(entityName, key, value,entity_key);

			masterEntityDaoNew.updateDataInMasterEntity(masterEntityModel);
		}

	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List <JSONObject> getAllPerson(long entityId) throws Exception {
		
		List<Map<String,String>> recordMapList = masterEntityDaoNew.getAllPerson(entityId);
		
		List<JSONObject> jsonObjectList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		
		for(Map<String, String> jsonMap : recordMapList) {
			
			for(Map.Entry<String, String> entry :jsonMap.entrySet()) {
				
				jsonObject.put(entry.getKey(), entry.getValue());
			}
			
			jsonObjectList.add(jsonObject);
			jsonObject = new JSONObject();
		}
	
		return jsonObjectList;
		
	}

	public List<JSONObject> getEntityByEntityIdList(long entityId,String entity_name, String attr_name, String value)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> recordMapList = masterEntityDaoNew.getEntityByEntityIdList(entityId,entity_name, attr_name, value);
		
		List<JSONObject> jsonObjectList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		
       for(Map<String, String> jsonMap : recordMapList) {
			
			for(Map.Entry<String, String> entry :jsonMap.entrySet()) {
				
				jsonObject.put(entry.getKey(), entry.getValue());
			}
			
			jsonObjectList.add(jsonObject);
			jsonObject = new JSONObject();
		}
		return jsonObjectList;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long getEntityIdFromEntityName(String entityName) throws Exception {
		return masterEntityDaoNew.getEntityIdFromEntityName(entityName);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public JSONObject getEntityById(long entity_key, String entity_name) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject jsonObj =masterEntityDaoNew.getEntityById(entity_key, entity_name);
		return jsonObj;
	}


	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject = masterEntityDaoNew.getEntityByEntityId(entity_name, attr_name, value);
		return jsonObject;
	}

	@Override
	public long getAttrCount(long entityId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}