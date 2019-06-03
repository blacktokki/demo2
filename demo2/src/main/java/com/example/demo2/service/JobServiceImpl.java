package com.example.demo2.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.example.demo2.crawler.Crawler;

@Service
public class JobServiceImpl implements JobService,AutoCompleteService,UtilService{
	@Autowired
	private Crawler programmersCrawler;
	
	@Autowired
	private Crawler saramInCrawler;
	
	@Override
	public JSONObject search(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String) map.get("keyword");
		List<String> result = new ArrayList<>();
		try {
			List<Map<?,?>> list=programmersCrawler.getAutoComp(keyword);
			for(Map<?,?> m:list)
				result.add(m.get(programmersCrawler.getCompName()).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,List<String>> result2=new HashMap<>();
		result2.put("result", result);
		JSONObject json=new JSONObject(result2);
		return json;
	}
	
	@Override
	public void jobs(Model model) {
		Map<String,Object> map= model.asMap();
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		Map<String,String> mapStr=new HashMap<>();
		requestMapper(request,mapStr,"keywords","java");//검색어
		requestMapper(request,mapStr,"category","4");//직무
		requestMapper(request,mapStr,"start","1");//시작번호
		mapStr.put("count", "20");
		//requestMapper(request,mapStr,"count","20");//개수
		requestMapper(request,mapStr,"career","신입");//경력
		mapStr.put("sr","directhire");
		Map<?,?> result=null;
		try {
			result=saramInCrawler.getPage(mapStr);
			model.addAttribute("result",result);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("struct",getDocumentStruct(
				"채용정보",
				"/html/category.html",
				"includes/list-job.jsp"));
		model.addAttribute("paging",getDocumentPaging(
				request,
				"start",
				Integer.parseInt((String)result.get("cnt"))/20));
		model.addAttribute("keywords",mapStr.get("keywords").replace("%20",","));
	}
}