<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- Paging 처리 -->
<div id="paging">
	<c:forEach items="${paging.url}" var="url" varStatus="status">
	 	<a href="${status.current}">${status.index+paging.minPage}</a>
	</c:forEach>
</div>