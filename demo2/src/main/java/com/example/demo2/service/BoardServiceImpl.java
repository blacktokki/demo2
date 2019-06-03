package com.example.demo2.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo2.domain.Board;
import com.example.demo2.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService,UtilService{
	@Autowired
	private BoardRepository boardRepository;
	@SuppressWarnings("unused")
	@Autowired
	private List<Board> boardDummy;
	
	@Override
	public void content(Model model) {
		Map<String,Object> map= model.asMap();
		Integer idx=(Integer)map.get("idx");
		Board board=boardRepository.findById(idx).get();
		model.addAttribute("board", board);
	}
	
	@Override
	public void write(Model model) {
		Map<String,Object> map= model.asMap();
		String keyword=(String)map.get("compName");
		try {
			model.addAttribute("compNameEncode",URLEncoder.encode(keyword,"UTF-8"));
			keyword=URLDecoder.decode(keyword,"UTF-8");
			model.addAttribute("compNameDecode",keyword);
		}
		catch(Exception e) {
		}
		model.addAttribute("struct",getDocumentStruct(
				"글작성",
				"/html/index.html",
				"includes/list-board.jsp"));
	}
		
	@Override
	public void save(Model model) {
		Map<String,Object> map= model.asMap();
		Board board=(Board)map.get("board");
		//System.out.println(board.getTitle());
		//System.out.println(board.getName());
		//System.out.println(board.getContent());
		//System.out.println(board.getCompName());
		boardRepository.save(board);
	}
}
