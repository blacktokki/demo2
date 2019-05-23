package com.example.demo2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo2.domain.Board;
import com.example.demo2.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private List<Board> boardDummy;
	
	public void content(Model model) {
		System.out.println(boardDummy.get(0).getHit());
		Map<String,Object> map= model.asMap();
		Integer idx=(Integer)map.get("idx");
		Board board=boardRepository.findById(idx).get();
		model.addAttribute("board", board);
	}
	
	public void list(Model model) {
		List<Board> boardList=boardRepository.findAll();
		model.addAttribute("board_list", boardList);
	}
	
	public void write(Model model) {
		Map<String,Object> map= model.asMap();
		Board board=(Board)map.get("board");
		boardRepository.save(board);
	}
}
