package com.example.demo2.service;

import org.springframework.ui.Model;

public interface CompanyService {
	public void companies(Model model);
	public void companiesInfo(Model model);
	public void companiesBoard(Model model);
}