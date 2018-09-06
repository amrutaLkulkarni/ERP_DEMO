package com.erpsystem.crms.controller;

import static com.erpsystem.crms.util.IErpUtils.convertToJsonMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erpsystem.crms.model.EntityIdModel;
import com.erpsystem.crms.model.LoginModel;
import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.service.ICrmsSvcNew;
import com.erpsystem.crms.util.IErpUtils;

@RestController
@RequestMapping(value = "/erp")
public class JmaErpController {

	@Autowired
	ICrmsSvcNew crmsSvcNew;
	
	@Autowired
	private Environment env;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String login(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		LoginModel loginModel = new LoginModel();
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			
			jsonMap = convertToJsonMap(inputJson);
			
			final String username = jsonMap.get("username");
			final String password = jsonMap.get("password");
			
			long randomNo = crmsSvcNew.validateWithDb(username, password);
			
			
			jsonObject.put("token", randomNo);
			loginModel.setRandomNo(Long.toString(randomNo));
			
			jsonObject.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
		
				
		 
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addBolb", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String addBlob(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String entityKey = null;	
		long entityid = 0;
		
		
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			 
			jsonMap = convertToJsonMap(inputJson);
			
			final String entity_name = jsonMap.get("entity_name");
			final String imagestring = jsonMap.get("imagestring");
			entityKey = jsonMap.get("entity_key_id");
			
			byte [] image = IErpUtils.basestringToString(imagestring);
			
			//entityKey = crmsSvcNew.getEntityKeyFromSystem(entity_name);
			
			entityid = crmsSvcNew.getEntityId(entity_name);
			
			crmsSvcNew.addRecordInSystem(entityid, entityKey, image);
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
		
				
		 
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateBlob", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" })
	public String updateBlob(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String entityKey = null;	
		long entityid = 0;
		
		
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			 
			jsonMap = convertToJsonMap(inputJson);
			
			final String entity_name = jsonMap.get("entity_name");
			final String imagestring = jsonMap.get("imagestring");
			entityKey = jsonMap.get("entity_key_id");
			
			byte [] image = IErpUtils.basestringToString(imagestring);
			
			//entityKey = crmsSvcNew.getEntityKeyFromSystem(entity_name);
			
			entityid = crmsSvcNew.getEntityId(entity_name);
			
			//crmsSvcNew.addRecordInSystem(entityid, entityKey, image);
			crmsSvcNew.updateBlob(entityid, entityKey, image);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject.toString();
		
				
		 
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addMultiBolb", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String addMultiImage(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String entitykey = null;	
		long entityid = 0;
		
		JSONObject item = new JSONObject(inputJson);
		JSONArray jsonArr =  (JSONArray) item.get("imagestring");
		int jsonArrLength = jsonArr.length();
		String imageStr = null;
		
		for(int i= 0;i<jsonArrLength;i++) {
			
			imageStr = (String) jsonArr.get(i);
	
			final String entity_name = item.getString("entity_name");
			final String imagestring = item.getString("imagestring");
			entitykey = item.getString("entity_key_id");
			
			byte [] image = IErpUtils.basestringToString(imagestring);
			
			entityid = crmsSvcNew.getEntityId(entity_name);
			
			crmsSvcNew.addMultiImage(entityid, entitykey, image);
		}
				
		
		
		return inputJson;
		
		
			
			
	
		 
	}

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateMultiBolb", method = RequestMethod.PUT, consumes = { "application/json" }, produces = { "application/json" })
	public String updateMultiImage(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String entitykey = null;	
		long entityid = 0;
		
		JSONObject item = new JSONObject(inputJson);
		JSONArray jsonArr =  (JSONArray) item.get("imagestring");
		int jsonArrLength = jsonArr.length();
		String imageStr = null;
		
		for(int i= 0;i<jsonArrLength;i++) {
			
			imageStr = (String) jsonArr.get(i);
	
			final String entity_name = item.getString("entity_name");
			final String imagestring = item.getString("imagestring");
			entitykey = item.getString("entity_key_id");
			
			byte [] image = IErpUtils.basestringToString(imagestring);
			
			entityid = crmsSvcNew.getEntityId(entity_name);
			
			crmsSvcNew.updateMultiImage(entityid, entitykey, image);
		}
				
		
		
		return inputJson;
		
		
			
			
	
		 
	}

	

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEntity", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public EntityIdModel addRecord(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		 //boolean tokenChk = crmsSvcNew.validateToken(token);
		 
		long entityKey = 0;
		EntityIdModel entityIdModel = new EntityIdModel();
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			
			
			Map<String,String> attrNotAvailMap = new HashMap<>();

			jsonMap = convertToJsonMap(inputJson);

			/*String token = jsonMap.get("token");
			
			boolean tokenChk = crmsSvcNew.validateToken(token);
			
			if(tokenChk)
			{*/
				
			final String entityName = jsonMap.get("entity_name");
			  entityKey = crmsSvcNew.getEntityKeyFromSystem(entityName);
			  entityIdModel.setEntityKey(entityKey);
		
			final List<String> attrList = crmsSvcNew.getCount(entityName);
			
			if(null!=jsonMap) {		

							
			
				for(final String atrName : attrList) {
					
						if(!jsonMap.containsKey(atrName)) {
							attrNotAvailMap.put(atrName, null);
						}
						
				}
				
			}
				
				if(null!=attrNotAvailMap) {
					
					jsonMap.putAll(attrNotAvailMap);
					
					if(jsonMap.containsKey(env.getProperty(entityName))) {
						jsonMap.remove(env.getProperty(entityName));
						//jsonMap.remove("token");
					}
					
				}
			
			
			final long jsonCount = jsonMap.size();

			if (attrList.size() == jsonCount) {

				crmsSvcNew.addRecordInSystem(entityKey, entityName, jsonMap);
				
				
			}

			else {
				
				throw new Exception();
			}
			
			
			// Add image code here.
			

		//}
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return entityIdModel;
		
		
		
		
	}

	@CrossOrigin(origins = "*") 
	@RequestMapping(value = "/updateEntity", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public String updateRecord(@RequestBody String inputJson, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String entity_key = null;
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			Map<String,String> attrNotAvailMap = new HashMap<>();

			jsonMap = convertToJsonMap(inputJson);

			final String entityName = jsonMap.get("entity_name");
			 entity_key = jsonMap.get("entity_key_id");
			// final long entityKey = crmsSvcNew.getEntityKeyFromSystemUpdate(entityName);
			final List<String> attrList = crmsSvcNew.getCount(entityName);
			//long jsonCount = jsonMap.size();
			
			List<MasterEntityModel> masterEntiyModelListt = new ArrayList<>();
			
			//
			
			if(null!=jsonMap) {
			
				for(final String atrName : attrList) {
					
						if(!jsonMap.containsKey(atrName)) {
							attrNotAvailMap.put(atrName, null);
						}
					
					
				}
				
			}
			
			//String id = env.getProperty("person");
			
			//System.out.println(id);
				
				if(null!=attrNotAvailMap) {
					
					jsonMap.putAll(attrNotAvailMap);
				
					if(jsonMap.containsKey("entity_name")) {
						jsonMap.remove("entity_name");
					}
					
					if(jsonMap.containsKey(env.getProperty(entityName))) {
						jsonMap.remove(env.getProperty(entityName));
					}
					
					
				}
				long jsonCount = jsonMap.size();
				
				//jsonMap.remove("entity_key_id");
				
			if (attrList.size() == jsonCount) {

				for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
					if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName")) {
						MasterEntityModel masterEntiyModelList = crmsSvcNew.getMasterEntityList(entityName, entry.getKey(), entry.getValue(), entity_key);
						masterEntiyModelListt.add(masterEntiyModelList);
					}

				}
				
				crmsSvcNew.updateRecordInSystem(masterEntiyModelListt);
				
			} else {
				throw new Exception();
			}
			
			
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return entity_key.toString();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEntity/{entity_name}", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json" })
	public String getAllRecord(@PathVariable("entity_name") String entity_name, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		final long entityId = crmsSvcNew.getEntityIdFromEntityName(entity_name);

		List<JSONObject> jsonObjectList = crmsSvcNew.getAllPerson(entityId);
		System.out.println(jsonObjectList.toString());

		// JSONObject jsonObject = crmsSvcNew.getAllPerson(entityId);

		return jsonObjectList.toString();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityByEntityIdList/{entity_name}/{attr_name}/{entity_key}", method = RequestMethod.GET, consumes = {
			"application/json", "application/xml" }, produces = { "application/json" })
	public String getEntityByEntityIdList(@PathVariable("entity_name") String entity_name,
			@PathVariable("attr_name") String attr_name, @PathVariable("entity_key") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		final long entityId = crmsSvcNew.getEntityIdFromEntityName(entity_name);

		List<JSONObject> jsonObjectList = crmsSvcNew.getEntityByEntityIdList(entityId, entity_name, attr_name, value);

		System.out.println(jsonObjectList.toString());

		return jsonObjectList.toString();

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityById/{entity_name}/{entity_key}", method = RequestMethod.GET, consumes = {
			"application/json", "application/xml" }, produces = { "application/json" })
	public String getEntityById(@PathVariable("entity_name") String entity_name,
			@PathVariable("entity_key") long entity_key, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JSONObject jsonObject = new JSONObject();

		jsonObject = crmsSvcNew.getEntityById(entity_key, entity_name);
		return jsonObject.toString();

	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityBlobById/{entity_id}/{entity_key}", method = RequestMethod.GET, consumes = {
			"application/json", "application/xml" }, produces = { "application/json" })
	public String getEntityBlobById( @PathVariable("entity_id") long entity_id,@PathVariable("entity_key") long entity_key,
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject = crmsSvcNew.getEntityBolbById(entity_key, entity_id);
		
		return jsonObject.toString();
		
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getMultiBlobById/{entity_id}/{entity_key}", method = RequestMethod.GET, consumes = {
			"application/json", "application/xml" }, produces = { "application/json" })
	public String getMultiBlobById( @PathVariable("entity_id") long entity_id,@PathVariable("entity_key") long entity_key,
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject = crmsSvcNew.getMultiBolbById(entity_key, entity_id);
				
		return jsonObject.toString();
	}
		
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityByEntityId/{entity_name}/{attr_name}/{entity_key}", method = RequestMethod.GET, consumes = {
			"application/json", "application/xml" }, produces = { "application/json" })
	public String getEntityByEntityId(@PathVariable("entity_name") String entity_name,
			@PathVariable("attr_name") String attr_name, @PathVariable("entity_key") String value,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject jsonObject = new JSONObject();

		jsonObject = crmsSvcNew.getEntityByEntityId(entity_name, attr_name, value);
		return jsonObject.toString();

	}

	
	
}