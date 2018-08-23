package com.erpsystem.crms.controller;

import static com.erpsystem.crms.util.IErpUtils.convertToJsonMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.erpsystem.crms.service.ICrmsSvcNew;

@RestController
@RequestMapping(value = "/erp")
public class JmaErpController {

	@Autowired
	ICrmsSvcNew crmsSvcNew;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEntity",method = RequestMethod.POST,
	consumes = {"application/json" , "application/xml"},produces = {"application/json" , "application/xml"})
	public void addRecord(@RequestBody String inputJson , HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		try {
			
		   Map<String,String> jsonMap = new HashMap<String,String>();
			
		   jsonMap = convertToJsonMap(inputJson);
		   
		   
		  
		   final String entityName = jsonMap.get("entity_name");
		   final long entityKey = crmsSvcNew.getEntityKeyFromSystem(entityName);
		   final long attrCount = crmsSvcNew.getCount(entityName);
		   final long jsonCount = jsonMap.size();
		   
		   if(attrCount == jsonCount) {
		   
		   crmsSvcNew.addRecordInSystem(entityKey,entityName,jsonMap);
		   }
		   /*for(Map.Entry<String, String> entry : jsonMap.entrySet()) {
			   
			  // if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName") ) {
			   if (null != entityName && !entry.getKey().equalsIgnoreCase("entity_name") ) {
				   crmsSvcNew.addRecordInSystem(entityKey,entityName,entry.getKey(),entry.getValue());
			   }
		  }	*/	 
		  else {
			  throw new Exception();
		}
		   
		}
		 catch (Exception exception) {
				exception.printStackTrace();
			} 		
	}
	
	/*@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addEntity",method = RequestMethod.POST,
	consumes = {"application/json" , "application/xml"},produces = {"application/json" , "application/xml"})
	public void addRecord(@RequestBody String inputJson , HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		try {
			
		   Map<String,String> jsonMap = new HashMap<String,String>();
			
		   jsonMap = convertToJsonMap(inputJson);
		   
		   final String entityName = jsonMap.get("entity_name");
		   final long entityKey = crmsSvcNew.getEntityKeyFromSystem(entityName);
		   
		   for(Map.Entry<String, String> entry : jsonMap.entrySet()) {
			   
			  // if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName") ) {
			   if (null != entityName && !entry.getKey().equalsIgnoreCase("entity_name") ) {
				   crmsSvcNew.addRecordInSystem(entityKey,entityName,entry.getKey(),entry.getValue());
			   }
		  }		  
		}
		 catch (Exception exception) {
				exception.printStackTrace();
			} 		
	}*/
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateEntity",method = RequestMethod.PUT,
	consumes = {"application/json" , "application/xml"},produces = {"application/json" , "application/xml"})
	public void updateRecord(@RequestBody String inputJson , HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			
			jsonMap = convertToJsonMap(inputJson);
			
			final String entityName = jsonMap.get("entity_name");
			final String entity_key = jsonMap.get("entity_key_id");
			//final long entityKey = crmsSvcNew.getEntityKeyFromSystemUpdate(entityName);
			final long attrCount = crmsSvcNew.getCount(entityName);
			 final long jsonCount = jsonMap.size();
			
			 if(attrCount == jsonCount) {
			 
			for(Map.Entry<String, String> entry : jsonMap.entrySet()) {				
			if (null != entityName && !entry.getKey().equalsIgnoreCase("entityName")) {				
				crmsSvcNew.updateRecordInSystem(entityName,entry.getKey(),entry.getValue(),entity_key);
				//
			}
			
		}
	}
			 else {
				  throw new Exception();
			}
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		
		
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEntity/{entity_name}",method = RequestMethod.GET,
	consumes = {"application/json" , "application/xml"},produces = {"application/json"})
		public String getAllRecord(@PathVariable("entity_name") String entity_name,  HttpServletRequest request,
			HttpServletResponse response)throws Exception {
	
		final long entityId = crmsSvcNew.getEntityIdFromEntityName(entity_name);
		
		List <JSONObject> jsonObjectList = crmsSvcNew.getAllPerson(entityId);
		System.out.println(jsonObjectList.toString());
		
		//JSONObject jsonObject = crmsSvcNew.getAllPerson(entityId);
		
		
		return jsonObjectList.toString();
		}
		
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityByEntityIdList/{entity_name}/{attr_name}/{entity_key}",method = RequestMethod.GET,
	consumes = {"application/json" , "application/xml"},produces = {"application/json"})
	public String getEntityByEntityIdList(@PathVariable("entity_name") String entity_name,@PathVariable ("attr_name") String attr_name, @PathVariable ("entity_key") String value , HttpServletRequest request,
			HttpServletResponse response)throws Exception  {
				
		final long entityId = crmsSvcNew.getEntityIdFromEntityName(entity_name);
		
		List <JSONObject> jsonObjectList = crmsSvcNew.getEntityByEntityIdList(entityId,entity_name, attr_name, value);
		
		System.out.println(jsonObjectList.toString());
		
		return jsonObjectList.toString();
		
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityById/{entity_name}/{entity_key}",method = RequestMethod.GET,
	consumes = {"application/json" , "application/xml"},produces = {"application/json"})
	public String getEntityById(@PathVariable("entity_name") String entity_name,@PathVariable ("entity_key") long entity_key , HttpServletRequest request,
			HttpServletResponse response)throws Exception  {
	
		JSONObject jsonObject = new JSONObject();
		
		jsonObject = crmsSvcNew.getEntityById(entity_key, entity_name);
		return jsonObject.toString();
		
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getEntityByEntityId/{entity_name}/{attr_name}/{entity_key}",method = RequestMethod.GET,
	consumes = {"application/json" , "application/xml"},produces = {"application/json"})
	public String getEntityByEntityId(@PathVariable("entity_name") String entity_name,@PathVariable ("attr_name") String attr_name, @PathVariable ("entity_key") String value , HttpServletRequest request,
			HttpServletResponse response)throws Exception  {
		
	
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject = crmsSvcNew.getEntityByEntityId(entity_name, attr_name, value);
		return jsonObject.toString();
		
	}
	
}
