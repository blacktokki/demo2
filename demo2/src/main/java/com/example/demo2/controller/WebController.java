package com.example.demo2.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo2.domain.Board;
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
		return "list";
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
	@GetMapping("/company/{keyword}/board")
	public String companyBoard(Model model,HttpServletRequest request,@PathVariable String keyword){
		model.addAttribute("request",request);
		model.addAttribute("keyword",keyword);
		companyService.companiesBoard(model);
		return "list";
	}
	
	@GetMapping("/board")
	public String board(Model model,@ModelAttribute("compName") String compName) throws Exception{
		model.addAttribute("compName",compName);
		boardService.write(model);
		return "board";
	}
	@PostMapping("/board")
	public String board(Model model,@ModelAttribute("board")Board board,@ModelAttribute("compNameEncode") String compName) throws Exception{
		boardService.save(model);
		return "redirect:/company/"+compName+"/board";
	}
	
	
	
	@GetMapping("/login")
	public String login() throws Exception{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model) throws Exception{
		return "redirect:/company";
	}
	
	@GetMapping("/temp/content")
	public String content() throws Exception{
		return "content";
	}
}