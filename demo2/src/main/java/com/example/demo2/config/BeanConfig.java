package com.example.demo2.config;



import java.util.*;
import com.example.demo2.domain.Board;

import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
    @Bean
    public JSONParser jsonParser(){
        return new JSONParser();
    }
    
    @Bean
    public List<Board> BoardDummy(){
    	List<Board> result=new ArrayList<>();
    	for(int i=0;i<3;i++) {
    		Board board=new Board();
    		board.setIdx(i+9000);
    		board.setTitle("제목");
    		board.setContent("내용");
    		board.setRegDate("2019.01.01");
    		board.setHit(1+i);
    		result.add(board);
    	}
    	return result;
    }
}

