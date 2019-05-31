<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id="board-info">
	<b>${struct.title} (전체 글: ${result.cnt}개)</b>
</div>
<c:if test='${article eq "includes/list-board.jsp"}'>
	<div id="board-name">
   		<a href="/board?compName=${keywordDecode}">글쓰기</a>
 	</div>
 </c:if>