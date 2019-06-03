<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:forEach items="${result.info}" var="info" varStatus="status">
	<tr>
	   <td id="number">
	   	${info.category}<br>
	   	${info.field}<br>
	   	${info.date}
	   	</td>
	   <td id="title">
	   		<strong>${info.title}</strong><br>
	   		${info.summary}
	   </td>
	</tr>
</c:forEach>