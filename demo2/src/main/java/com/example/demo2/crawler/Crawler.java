package com.example.demo2.crawler;

import java.util.*;
import org.json.simple.parser.JSONParser;


public interface Crawler{
	static JSONParser jpr = new JSONParser();
	
	default Object stringParse(String string) throws Exception{
		Object obj;
		synchronized (jpr) {
			obj=jpr.parse(string);
		}
		return obj;
	}
	default Map<?,?> getAutoFirst(List<Map<?,?>> list) throws Exception{
		return list.get(0);
	};
	
	public List<Map<?,?>> getAutoComp(String keyword)throws Exception;
	public String getCompName();
	public String getIco();
	public Map<?,?> getPage(Map<?,?> map)throws Exception;
}
