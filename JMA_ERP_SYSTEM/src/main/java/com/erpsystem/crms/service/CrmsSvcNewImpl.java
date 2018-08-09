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

	@Transactional(propagation = Propagation.REQUIRED)
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
	}

	public long getEntityKeyFromSystem(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		return entityKey + 1;

	}

	public long getEntityKeyFromSystemUpdate(final String entityName) throws Exception {

		long entityKey = masterEntityDaoNew.getIdentifier(entityName);
		return entityKey;

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
			final String value) throws Exception {

		final long eaid = masterEntityDaoNew.getEaidUpdate(ent_name, attr_name);

		final long entityKey = masterEntityDaoNew.getEntityKeyFromEaid(eaid);

		MasterEntityModel masterDetailsModel = new MasterEntityModel();

		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setValue(value);

		return masterDetailsModel;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRecordInSystem(String entityName, String key, String value) throws Exception {
		// TODO Auto-generated method stub
		if (null != masterEntityDaoNew) {
			MasterEntityModel masterEntityModel = getMasterEntityModelForUpdate(entityName, key, value);

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
		
		
		
		/*for(Map.Entry<String, String> entry : recordMap.entrySet()) {
			
			jsonObject.put(entry.getKey(), entry.getValue());
			
		}*/
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
}