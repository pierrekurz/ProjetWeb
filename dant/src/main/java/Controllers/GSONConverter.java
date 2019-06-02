package Controllers;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GSONConverter {

	
	
	public String toJson(HashMap<String,String> lineResult ){
		 Gson gson = new Gson();
		 String serialized = gson.toJson(lineResult);
		 return serialized;
		}
	
	public String listToJson(List<String> lineResult ){
		 Gson gson = new Gson();
		 String serialized = gson.toJson(lineResult);
		 return serialized;
		}
	

		public HashMap<String,String> jsonToString(String lineResult){
		 Gson gson = null;
		 HashMap<String,String> map = gson.fromJson(lineResult, HashMap.class);
		 return map;
		}
		
		public List<String> jsonToList(String lineResult){
			 Gson gson = null;
			 List<String> map = gson.fromJson(lineResult, HashMap.class);
			 return map;
			}
		
		public List<Object[]> jsonToArrayObject(String lineResult){
			 Gson gson = null;
			 List<Object[]> list = gson.fromJson(lineResult, HashMap.class);
			 return list;
			}

		public String listArrayToJson(List<Object[]> subList) {
			Gson gson = new Gson();
			 String serialized = gson.toJson(lineResult);
			 return serialized;
		}

		
}
