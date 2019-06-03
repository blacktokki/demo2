<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:forEach items="${result.boards}" var="board" varStatus="status">
	<tr>
	   <td id="number">
	   		${board.name}<br>
	   		${board.category}<br>
	   		${board.regDate}
	   	</td>
	   <td id="title">
	   		<strong>${board.title}</strong><br>
	   		${board.content}
	   </td>
	</tr>
</c:forEach>