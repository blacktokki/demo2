<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="centered">
	<form action="/board" method="post">
		<input type="hidden" name="compName" value="${compNameDecode}"/>
		<input type="hidden" name="compNameEncode" value="${compNameEncode}"/>
		회사명&nbsp;${compName}<br>
		이름<input type="text" name="name" size="50"><br>
		제목<input type="text" name="title" size="50"><br>
		<script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
		<textarea id="editor" name="content"></textarea><br>
		<script>
			CKEDITOR.replace( 'editor', {
			} );
		</script>
		<input type="submit" value="입력">&nbsp;&nbsp;<a href="/company/${compNameEncode}/board">목록보기</a>
	</form>
</div>
