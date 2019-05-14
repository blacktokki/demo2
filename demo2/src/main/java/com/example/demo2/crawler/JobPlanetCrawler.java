package com.example.demo2.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

@Component
public class JobPlanetCrawler implements Crawler{
	
	public List<Map<?,?>> getAutoComp(String keyword) throws Exception{
		String url="https://www.jobplanet.co.kr/autocomplete/autocomplete/suggest.json?term="+keyword;
		Document doc = Jsoup.connect(url).ignoreContentType(true).get();
		JSONObject temp =(JSONObject)stringParse(doc.text());
		
		@SuppressWarnings("unchecked")
		List<Map<?,?>> temp2=(List<Map<?,?>>)temp.get("companies");
		return temp2;
	}
	
	@Override
	public String getCompName() {
		return "value";
	}
	
	@Override
	public String getIco() {
		return "JobP";
	}
	
	@Override
	public Map<?,?> getPage(Map<?,?> map) throws Exception{
		Long code= (Long)map.get("id");
		String url="https://www.jobplanet.co.kr/companies/"+code+"/reviews/";
		Document doc = Jsoup.connect(url).get();
		Elements div=doc.select("#contents_wrap");
		Map<String,String> map2=new HashMap<>();
		Elements div2,div3;
			div3=div.select(".stats_ttl");
			map2.put("전체 리뷰 통계", div3.get(0).text());
		  
			div2=div.select(".rate_txt");
			div3=div.select(".rate_point");
			map2.put(div2.get(0).text(), div3.get(0).text());
		  
			div2=div.select(".rate_bar_set dt");
			div3=div.select(".rate_bar_set dd");
			for(int i=0;i<div2.size();i++) 
				map2.put(div2.get(i).text(),div3.get(i).text());
		
		System.out.println(map2.toString());
		return map2;
	};
}
