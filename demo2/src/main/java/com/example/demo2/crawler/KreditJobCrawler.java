package com.example.demo2.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Component
public class KreditJobCrawler implements Crawler{
	
	@Override
	public List<Map<?,?>> getAutoComp(String keyword) throws Exception{
		String url="https://kreditjob.com/api/search/autocomplete?q="+keyword+"&index=1&size=5";
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		//System.out.println(doc.toString());
		JSONObject temp =(JSONObject)stringParse(doc.text());
		JSONArray arr=(JSONArray)temp.get("docs");
		return getAutoComp(arr);
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
		Document doc = Jsoup.connect(url).data(mapStr(map)).ignoreContentType(true).post();
		Elements body=doc.select("body");
		Map<?,?> obj=(Map<?, ?>)stringParse(body.text());
		Map<String,String> result=mapStr((Map<?, ?>)obj.get("companyInfoData"));
		System.out.println(result.toString());
		result.put("CompName", (String) map.get(getCompName()));
		result.put("url", url);
		return result;
	}
}
