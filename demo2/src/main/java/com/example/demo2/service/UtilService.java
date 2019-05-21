package com.example.demo2.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UtilService {
	default void requestMapper(HttpServletRequest request,Map<String,String> map,String keyword,String defval){
		String val;
		try {
			val=((String)request.getParameter(keyword)).replace(" ", "%20");
		}
		catch(Exception e) {
			val=defval;
		}
		if(!val.equals(""))
			map.put(keyword,val);
	}
}
