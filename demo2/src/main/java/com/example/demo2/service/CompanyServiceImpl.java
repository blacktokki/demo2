package com.example.demo2.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.example.demo2.crawler.Crawler;
import com.example.demo2.crawler.SaramInCrawler;

@Service
public class CompanyServiceImpl implements CompanyService,AutoCompleteService,UtilService{
	@Autowired
	private Crawler kreditJobCrawler;
	@Autowired
	private Crawler jobPlanetCrawler;
	@Autowired
	private Crawler indeedCrawler;
	@Autowired
	private SaramInCrawler saramInCrawler;
	
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
					for(Map<?,?> obj:list) {
						String bf=(String) obj.get(crawler.getCompName());
						//System.out.println(bf);
						bf=bf.replaceAll("\\((.)\\)", "");
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
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject search(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String) map.get("keyword");
		List<String> result = new ArrayList<>();
		Map<String,List<String>> icos = new HashMap<>();
		try {
			keyword=URLEncoder.encode(keyword,"UTF-8");
			SearchThread threadK=new SearchThread(
					kreditJobCrawler,keyword,result,icos);
			SearchThread threadJ=new SearchThread(
					jobPlanetCrawler,keyword,result,icos);
			SearchThread threadI=new SearchThread(
					indeedCrawler,keyword,result,icos);
			threadK.join();
			threadI.join();
			threadJ.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//for(String s:result)
			//System.out.print(s+" "+icos.get(s).toString());
		JSONObject json=new JSONObject();
		json.put("result", result);
		json.put("icos", icos);
		return json;
	}
	
	private void companyInfo(Crawler crawler,String keyword,Model model,Map<String,String> mapAll) throws Exception{
		Map<?,?> map=crawler.getAutoFirst(crawler.getAutoComp(keyword));
		String keyword2=(String) map.get(crawler.getCompName());
		//System.out.println(keyword+"::"+keyword2);
		if(keyword.replace("(주)", "").equals(keyword2.replace("(주)", ""))) {
			mapAll.put(crawler.getIco(),keyword2);
			model.addAttribute(crawler.getIco(),crawler.getPage(map));
		}
	}
	
	@Override
	public void companies(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String) map.get("keyword");
		Map<String,String> mapAll=new HashMap<>();
		try {
			keyword=URLDecoder.decode(keyword, "UTF-8");
			companyInfo(kreditJobCrawler,keyword,model,mapAll);
			companyInfo(jobPlanetCrawler,keyword,model,mapAll);
			companyInfo(indeedCrawler,keyword,model,mapAll);
		}
		catch(Exception e) {
		}
		model.addAttribute("icos",mapAll);
	}

	@Override
	public void companiesInfo(Model model) {
		Map<String,Object> map= model.asMap();
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		Map<String,String> mapStr=new HashMap<>();
		requestMapper(request,mapStr,"category","");//분류
		requestMapper(request,mapStr,"page","1");//시작번호
		requestMapper(request,mapStr,"pageCount","20");//개수
		try{
			mapStr.put("searchword",URLDecoder.decode((String)map.get("keyword"),"UTF-8"));//검색어
			model.addAttribute(saramInCrawler.getPageInfo(mapStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}