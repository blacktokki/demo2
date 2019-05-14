package com.example.demo2.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private void getPageMapper(Map<String,String> map,Element ele,String key){
		map.put(key,ele.select(key).text());
	}
	
	@Override
	public Map<?,?> getPage(Map<?,?> map) throws Exception{
		String url="http://api.saramin.co.kr/job-search";
		@SuppressWarnings("unchecked")
		Map<String,String> mapStr=(Map<String,String>)map;
		mapStr.put("sr","directhire");
		System.out.println(mapStr);
		Document doc = Jsoup.connect(url).data(mapStr).ignoreContentType(true).get();
		Elements div=doc.select("job");
		Map<String,Object> map2=new HashMap<>();
		List<Map<String,String>> list=new ArrayList<>();
		for(Element ele:div){
			Map<String,String> map3=new HashMap<>();
			//System.out.println(ele.toString());
			getPageMapper(map3,ele,"url");
			getPageMapper(map3,ele,"name");
			//map3.put("name-href",ele.select("name").attr("href"));
			getPageMapper(map3,ele,"title");
			getPageMapper(map3,ele,"expiration-timestamp");
			getPageMapper(map3,ele,"close-type");
			getPageMapper(map3,ele,"location");
			getPageMapper(map3,ele,"job-type");
			getPageMapper(map3,ele,"industry");
			getPageMapper(map3,ele,"job-category");
			getPageMapper(map3,ele,"experience-level");
			getPageMapper(map3,ele,"required-education-level");
			getPageMapper(map3,ele,"keyword");
			getPageMapper(map3,ele,"salary");
			list.add(map3);
		}
		map2.put("jobs",list);
		return map2;
	};
}
