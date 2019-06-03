package com.example.demo2.service;

import java.util.*;

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
	default Map<?,?> getDocumentStruct(String title,String searchBox,String article){
		Map<String,String> map=new HashMap<>();
		map.put("title",title);
		map.put("searchBox",searchBox);
		map.put("article",article);
		return map;
	}
	public static String changeURLParam(HttpServletRequest request,String n,String v){
	    Enumeration<?> param = request.getParameterNames();
	    StringBuffer strParam = new StringBuffer();
	    StringBuffer strURL = new StringBuffer();
	    //if (param.hasMoreElements())
	    strParam.append("?");
	    String addParam=n+"="+ v;
	    while (param.hasMoreElements()){
	      String name = (String) param.nextElement();
	      String value = request.getParameter(name);
	      if(!name.equals(n)) {
	    	  strParam.append(name + "=" + value);
	      //if (param.hasMoreElements())
	      	strParam.append("&");
	      }
	    }
	    strURL.append(request.getRequestURI());
	    strURL.append(strParam);
	    strURL.append(addParam);
	    return strURL.toString();
	}

	
	default Map<?,?> getDocumentPaging(HttpServletRequest request,String pageStr,Integer total){
		Map<String,Object> map=new HashMap<>();
		String page=request.getParameter(pageStr);
		if(page==null)
			page="1";
		map.put("page",page);
		Integer minPage=Math.max(Integer.parseInt(page)-5,1);
		map.put("minPage",minPage);
		Integer maxPage=Math.min(minPage+10,total);
		List<String> list=new ArrayList<>();
		for(Integer i=minPage;i<maxPage;i++) 
			list.add(changeURLParam(request,pageStr,Integer.toString(i)));
		map.put("url", list);
		return map;
	}
}
