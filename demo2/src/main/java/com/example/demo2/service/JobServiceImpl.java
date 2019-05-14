package com.example.demo2.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.example.demo2.crawler.Crawler;

@Service
public class JobServiceImpl implements JobService,AutoCompleteService{
	@Autowired
	private Crawler programmersCrawler;
	
	@Autowired
	private Crawler saramInCrawler;
	/*
	public class SearchThread extends Thread{
		private Crawler crawler;
		private String keyword;
		private List<String> result;
		private Map<String,List<String>> icos;
		
		public SearchThread(Crawler crawler,String keyword,List<String> result,Map<String,List<String>> icos) {
			this.crawler=crawler;
			this.keyword=keyword;
			this.result=result;
			this.icos=icos;
			this.start();
		}
		
		@Override
		public void run() {
			try {
				List<Map<?,?>> list=crawler.getAutoComp(keyword);
				synchronized (result) {
					String ico=crawler.getIco();
					for(Map<?,?> i:list) {
						String bf=(String) i.get(crawler.getCompName());
						//System.out.println(bf);
						bf=bf.replace("(주)", "");
						List<String> lii= new ArrayList<>();
						if (icos.get(bf)==null) {
							result.add(bf);
							icos.put(bf,lii);
						}
						//System.out.print(bf);
						//System.out.println(ico.get(bf));
						if(icos.get(bf).contains(ico)==false)
							icos.get(bf).add(ico);
					}
				}
				//System.out.println(result.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
	
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject search(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String) map.get("keyword");
		List<String> result = new ArrayList<>();
		try {
			List<Map<?,?>> list=programmersCrawler.getAutoComp(keyword);
			System.out.println(list.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//for(String s:result)
			//System.out.print(s+" "+icos.get(s).toString());
		JSONObject json=new JSONObject();
		json.put("result", result);
		return json;
	}
	
	void jobInfoMapper(HttpServletRequest request,Map<String,String> map,String keyword,String defval){
		String val=(String)request.getParameter(keyword);
		map.put(keyword,(val!=null?val:defval));
	}
	
	@Override
	public void jobInfo(Model model) {
		Map<String,Object> map= model.asMap();
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		Map<String,String> mapStr=new HashMap<>();
		jobInfoMapper(request,mapStr,"keywords","java");//검색어
		jobInfoMapper(request,mapStr,"category","4");//직무
		jobInfoMapper(request,mapStr,"start","0");//시작번호
		jobInfoMapper(request,mapStr,"count","10");//개수
		jobInfoMapper(request,mapStr,"career","신입");//경력
		Map<?,?> result;
		try {
			result=saramInCrawler.getPage(mapStr);
			System.out.println(result.toString());
			model.addAttribute("result",result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}