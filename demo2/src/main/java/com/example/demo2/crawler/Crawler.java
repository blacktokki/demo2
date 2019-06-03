package com.example.demo2.crawler;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.example.demo2.config.ApplicationContextProvider;


public interface Crawler{
	public JSONParser JPAR=new JSONParser();
	
	default Object stringParse(String string) throws Exception{
		Object obj;
		synchronized (JPAR) {
			obj=JPAR.parse(string);
		}
		return obj;
	}
	
	default List<Map<?,?>> getAutoComp(JSONArray arr){
		List<Map<?,?>> list = new ArrayList<>();
		for (Object obj : arr){ 
			list.add((Map<?,?>)obj);
		} 
		return list;
	}
	
	default Map<?,?> getAutoFirst(List<Map<?,?>> list) throws Exception{
		return list.get(0);
	};
	
	default Map<String, String> mapStr(Map<?, ?> map) {
		Map<String, String> result=new HashMap<>();
        for ( Map.Entry<?,?> e: map.entrySet()){
            String k=e.getKey().toString();
            String v=e.getValue().toString();  
            result.put(k, v);
        }
        return result; 
    }
	
	public List<Map<?,?>> getAutoComp(String keyword)throws Exception;
	public String getCompName();
	public String getIco();
	public Map<?,?> getPage(Map<?,?> map)throws Exception;
}
