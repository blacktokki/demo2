package com.example.demo2.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo2.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	 List<Board> findByCompName(String compName, Pageable pageable);
	 long countByCompName(String compName);
}
