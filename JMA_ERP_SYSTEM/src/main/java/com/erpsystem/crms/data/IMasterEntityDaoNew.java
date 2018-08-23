package com.erpsystem.crms.data;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.erpsystem.crms.model.MasterEntityModel;

public interface IMasterEntityDaoNew {

	//ADDENTITY
	public long getEaId(String ent_name, String attr_name)throws Exception;

	public long getLatestEntityKeyAndIncrement(String entity_name)throws Exception;
	
	public void addDataInMasterEntity(MasterEntityModel masterEntityModel)throws Exception;

	public long getIdentifier(String entity_name) throws Exception;
	
	

	//UPDATEENTITY
	public long getEaidUpdate(String ent_name, String attr_name)throws Exception;
	
	public long getEntityKeyFromEaid(long eaid)throws Exception;
	
	public void updateDataInMasterEntity(MasterEntityModel masterEntityModel)throws Exception;
	
	//GETALLENTITY
	public List <Map<String,String>> getAllPerson(long entityId) throws Exception;

	public long getEntityIdFromEntityName(String entityName)throws Exception;
	
	//GETENTITYBYID
	
	public JSONObject getEntityById (long entity_key, String entity_name ) throws Exception;

	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value)throws Exception;

	public List<Map<String, String>> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)throws Exception;

	public void addDataInMasterEntity(long entityKey, String ent_name, Map<String, String> jsonMap) throws Exception;
	
	public long getAttrCount(final long entityId) throws Exception;
	
	public long getCount(final String entityName) throws Exception;
}	

	
