package com.example.demo2.crawler;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ProgrammersCrawler implements Crawler{
	
	public List<Map<?,?>> getAutoComp(String keyword) throws Exception{
		String url="https://programmers.co.kr/tags/auto_complete?term="+keyword;
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		JSONObject temp =(JSONObject)stringParse(doc.text());
		@SuppressWarnings("unchecked")
		List<Map<?,?>> temp2=(List<Map<?,?>>)temp.get("results");
		return temp2;
	}
	
	@Override
	public String getCompName() {
		return "text";
	}
	@Override
	public String getIco() {
		return "ProG";
	}
	
	@Override
	public Map<?,?> getPage(Map<?,?> map) throws Exception{		
		return null;
	};
}
