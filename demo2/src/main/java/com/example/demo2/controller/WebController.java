package com.example.demo2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
//import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class WebController {
  
  @GetMapping("/company")
  public String company(Model model) throws Exception{
	  List<String> nameList = new ArrayList<String>(Arrays.asList("홍길동", "김철수", "박영희"));
	  model.addAttribute("nameList", nameList);

	  return "company";
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
