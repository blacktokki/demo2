package com.example.demo2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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
	
	@ResponseBody
	@GetMapping("/company/auto")
    public JSONObject testAuto(Model model,@RequestParam String keyword){
		model.addAttribute("keyword",keyword);
    	return ((AutoCompleteService)companyService).search(model);
    }
	
	@ResponseBody
	@GetMapping("/search/auto")
    public JSONObject auto(Model model,@RequestParam String keyword){
		model.addAttribute("keyword",keyword);
    	return ((AutoCompleteService)jobService).search(model);
    }
	
	@GetMapping("/company/{keyword}")
	public String company(Model model,@PathVariable String keyword){
		model.addAttribute("keyword",keyword);
		companyService.companyInfo(model);
		
		List<String> nameList = new ArrayList<String>(Arrays.asList("홍길동", "김철수", "박영희"));
		model.addAttribute("nameList", nameList);
		return "company";
	}
	@GetMapping("/search")
	public String job(Model model,HttpServletRequest request){
		model.addAttribute("request",request);
		jobService.jobInfo(model);
		return "job";
	}
	
	@GetMapping("/login")
	public String login() throws Exception{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model) throws Exception{
		return "redirect:/company";
	}
}
