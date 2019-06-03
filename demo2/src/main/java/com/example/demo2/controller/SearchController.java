package com.example.demo2.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.service.AutoCompleteService;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	AutoCompleteService companyServiceImpl;
	@Autowired
	AutoCompleteService jobServiceImpl;
	
	@GetMapping("/auto")
    public JSONObject testAuto(Model model,@RequestParam String keyword){
		model.addAttribute("keyword",keyword);
    	return companyServiceImpl.search(model);
    }
	
	@GetMapping("/auto/category")
    public JSONObject auto(Model model,@RequestParam String keyword){
		model.addAttribute("keyword",keyword);
    	return jobServiceImpl.search(model);
    }
}
