package com.example.demo2.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;

import org.json.simple.JSONObject;

@Component
public class KreditJobCrawler implements Crawler{
	
	@Override
	public List<Map<?,?>> getAutoComp(String keyword) throws Exception{
		String url="https://kreditjob.com/api/search/autocomplete?q="+keyword+"&index=1&size=5";
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		//System.out.println(doc.toString());
		JSONObject temp =(JSONObject)stringParse(doc.text());
		@SuppressWarnings("unchecked")
		List<Map<?,?>> temp2=(List<Map<?,?>>)temp.get("docs");
		return temp2;
	}
	
	@Override
	public String getCompName() {
		return "CMPN_NM";
	}
	
	@Override
	public String getIco() {
		return "KreditJ";
	}
	
	@Override
	public Map<?,?> getPage(Map<?,?> map) throws Exception{
		String url="https://kreditjob.com/api/company/companyPage";
		@SuppressWarnings("unchecked")
		Document doc = Jsoup.connect(url).data((JSONObject)map).ignoreContentType(true).post();
		Elements body=doc.select("body");
		Map<?, ?> obj=(JSONObject)stringParse(body.text());
		return obj;
	}
}
