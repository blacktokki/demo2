<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div id="board-info">
	<b>${struct.title} (전체 글: ${result.cnt}개)</b>
</div>
<c:if test='${struct.article eq "includes/list-board.jsp"}'>
	<div id="board-name">
   		<a href="/board?compName=${keywordEncode}">글쓰기</a>
 	</div>
 </c:if>

<c:if test="${!empty keywords}">
	<input type="hidden" id="tags-load" value="${keywords}"/>
	<script>
		var str=$("#tags-load").val();
		var arr=str.split(',');
		var result="";
		for( var i in arr) {
			console.log(arr[i]);
			result+="<div class='tag-skill'>"+arr[i]+"</div>";
		};
		$('#tags').html(result);
	</script>
</c:if>
