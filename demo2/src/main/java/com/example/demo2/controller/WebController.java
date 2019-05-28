package com.example.demo2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo2.service.*;

@Controller
public class WebController {
  
	@Autowired
	CompanyService companyService;
	@Autowired
	JobService jobService;
	@Autowired
	BoardService boardService;
	
	@GetMapping({"/","/welcome","/welcome/index"})
	public String index() throws Exception{
		return "index";
	}
	
	@GetMapping("/welcome/category")
	public String category() throws Exception{
		return "category";
	}
	
	@GetMapping("job/result")
	public String job(Model model,HttpServletRequest request){
		model.addAttribute("request",request);
		jobService.jobs(model);
		List<String> nameList = new ArrayList<String>(Arrays.asList("홍길동", "김철수", "박영희"));
		model.addAttribute("nameList", nameList);
		return "job";
	}
	
	@GetMapping("/company/{keyword}")
	public String company(Model model,@PathVariable String keyword){
		model.addAttribute("keyword",keyword);
		companyService.companies(model);
		return "content";
	}
	
	@GetMapping("/company/{keyword}/info")
	public String companyInfo(Model model,HttpServletRequest request,@PathVariable String keyword){
		model.addAttribute("request",request);
		model.addAttribute("keyword",keyword);
		companyService.companiesInfo(model);
		return "list";
	}
	
	@GetMapping("/login")
	public String login() throws Exception{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model) throws Exception{
		return "redirect:/company";
	}
	
	@GetMapping("/temp/list")
	public String board() throws Exception{
		return "list";
	}
	
	@GetMapping("/temp/content")
	public String content() throws Exception{
		return "content";
	}
	@GetMapping("/temp/board")
	public String contentEdit() throws Exception{
		return "board";
	}
}