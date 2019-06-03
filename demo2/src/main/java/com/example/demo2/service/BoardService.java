package com.example.demo2.service;

import org.springframework.ui.Model;

public interface BoardService {
	public void content(Model model);
	public void save(Model model);
	public void write(Model model);
}
