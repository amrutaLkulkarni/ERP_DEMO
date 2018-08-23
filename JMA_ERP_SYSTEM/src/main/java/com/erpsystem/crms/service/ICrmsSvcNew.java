package com.erpsystem.crms.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface ICrmsSvcNew {

	public void addRecordInSystem(final long entityKey,String entityName,Map<String,String> jsonMap)throws Exception;
	
	
	public long getEntityKeyFromSystem(final String entityName) throws Exception;
	
	//public long getEntityKeyFromSystemUpdate(final String entityName)throws Exception;

	public void updateRecordInSystem(String entityName, String key, String value, String entity_key)throws Exception;

	public List<JSONObject> getAllPerson(long entityId) throws Exception;


	public long getEntityIdFromEntityName(String entityName) throws Exception;
	
	public JSONObject getEntityById (long entity_key, String entity_name ) throws Exception;


	public JSONObject getEntityByEntityId(String entity_name, String attr_name, String value) throws Exception;


	public List<JSONObject> getEntityByEntityIdList(long entityId, String entity_name, String attr_name, String value)throws Exception;
	
	public long getAttrCount(final long entityId) throws Exception;
	
	public long getCount(final String entityName) throws Exception;
}
