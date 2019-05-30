<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="board-info">
	<b>게시판 (전체 글: ${result.cnt})</b>
</div>
     
<div id="board-name">
   <a href="/bbs/writeForm.bbs?pageNum=${'pageNum'}">글쓰기</a>
 </div>