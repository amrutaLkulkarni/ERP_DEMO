package com.erpsystem.crms.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface IErpUtils {

	static Map<String,String> convertToJsonMap(String json) throws IOException, JSONException {
		
		Map<String,String> hm=new HashMap<String,String>();
		
		if (null != json) {
			
			String data = json.toString();
			String name = null;
			JSONObject item = new JSONObject(data);

			JSONArray list = item.names();
		
			for (int i = 0; i < list.length(); i++) {
				name = list.getString(i);
				
				System.out.println(name + ": " + item.get(name).toString());
				
				hm.put(name, item.get(name).toString());
			}
			
			System.out.println(hm);
		}
		
		return hm;
	}

}
