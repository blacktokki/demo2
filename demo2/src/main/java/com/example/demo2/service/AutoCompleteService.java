package com.example.demo2.service;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;

public interface AutoCompleteService {
	public JSONObject search(Model model);
}
