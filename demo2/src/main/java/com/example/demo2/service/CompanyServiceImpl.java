package com.example.demo2.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.example.demo2.crawler.Crawler;
import com.example.demo2.crawler.SaramInCrawler;
import com.example.demo2.domain.Board;
import com.example.demo2.repository.BoardRepository;

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
	@Autowired
	private BoardRepository boardRepository;
	//@Autowired
	//private List<Board> boardDummy;
	
	private String reg(String string) {
		return string.replaceAll("\\((.)\\)", "");
	}
	
	public class SearchThread extends Thread{
		private Crawler crawler;
		private String keyword;
		private List<String> result;
		private Map<String,List<String>> icos;
		
		public SearchThread(Crawler crawler,String keyword,List<String> result
				,Map<String,List<String>> icos) {
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
						bf=reg(bf);
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

	private void companies(Crawler crawler,String keyword,Map<String,Object> result) throws Exception{
		Map<?,?> map=crawler.getAutoFirst(crawler.getAutoComp(keyword));
		String keyword2=(String) map.get(crawler.getCompName());
		//System.out.println(keyword+"::"+keyword2);
		if(reg(keyword).equals(reg(keyword2)))
			result.put(crawler.getIco(),crawler.getPage(map));
	}
	
	@Override
	public void companies(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String) map.get("keyword");
		Map<String,Object> result=new HashMap<>();
		try {
			keyword=URLDecoder.decode(keyword, "UTF-8");
			companies(kreditJobCrawler,keyword,result);
			companies(jobPlanetCrawler,keyword,result);
			companies(indeedCrawler,keyword,result);
			System.out.println(result.toString());
		}
		catch(Exception e) {
		}
		model.addAttribute("result",result);
		model.addAttribute("struct",getDocumentStruct(
				keyword,
				"/html/index.html",
				"includes/content-company.jsp"));
	}

	@Override
	public void companiesInfo(Model model) {
		Map<String,Object> map= model.asMap();
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		Map<String,String> mapStr=new HashMap<>();
		requestMapper(request,mapStr,"category","");//분류
		requestMapper(request,mapStr,"page","1");//시작번호
		String keyword="";
		Map<?,?> result=null;
		try{
			
			keyword=URLDecoder.decode((String)map.get("keyword"),"UTF-8");
			mapStr.put("searchword",keyword);//검색어
			result=saramInCrawler.getPageInfo(mapStr);
			model.addAttribute("result",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("struct",getDocumentStruct(
				keyword+" 자료실",
				"/html/index.html",
				"includes/list-info.jsp"));
		model.addAttribute("paging",getDocumentPaging(
				request,
				"page",
				Integer.parseInt((String)result.get("cnt"))/20));
	}
	
	@Override
	public void companiesBoard(Model model) {
		Map<String,Object> map= model.asMap();
		HttpServletRequest request=((HttpServletRequest) map.get("request"));
		String keyword=(String)map.get("keyword");
		try {
			model.addAttribute("keywordEncode",URLEncoder.encode(keyword,"UTF-8"));
			keyword=URLDecoder.decode(keyword,"UTF-8");
			model.addAttribute("keywordDecode",keyword);
		}
		catch(Exception e) {
		}
		List<Board> boards=boardRepository.findByCompName(keyword,PageRequest.of(0, 20, Sort.by("regDate")));//boardDummy;
		Map<String,Object> result=new HashMap<>();
		//System.out.println(boards.toString());
		result.put("boards", boards);
		result.put("cnt", boardRepository.countByCompName(keyword));
		for(Board board:boards) {
			String str=board.getContent();
			board.setContent(str.length()>30?str.substring(0,30)+"...":str);
		}
		model.addAttribute("result",result);
		model.addAttribute("struct",getDocumentStruct(
				keyword+" 게시판",
				"/html/index.html",
				"includes/list-board.jsp"));
		model.addAttribute("paging",getDocumentPaging(
				request,
				"count",
				((Long)result.get("cnt")).intValue()/20));
	}
}