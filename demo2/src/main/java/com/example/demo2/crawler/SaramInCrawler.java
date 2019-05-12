package com.example.demo2.crawler;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SaramInCrawler implements Crawler{
	
	public List<Map<?,?>> getAutoComp(String keyword) throws Exception{
		return null;
	}
	
	@Override
	public String getCompName() {
		return "";
	}
	@Override
	public String getIco() {
		return "SaramI";
	}
	
	@Override
	public Map<?,?> getPage(Map<?,?> map) throws Exception{
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		String keyword=(String)request.getAttribute("keyword");//검색어
		String category=(String)request.getAttribute("category");//직무
		String career=(String)request.getAttribute("career");//경력
		String url="http://api.saramin.co.kr/job-search?keywords="+keyword+"&job_category="+category;
		
		return null;
	};
}
