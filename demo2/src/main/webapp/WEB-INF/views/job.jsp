<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>회사</title>
		<meta charset="utf-8" />
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
	</head>
	

	<body class="is-preload">
		<!-- Header -->
		<c:import url="/html/header.html" charEncoding="UTF-8"/>
		<!-- Main -->
		<main id="main">
			<!--
			<c:set var="____" value="${____}" scope="request"/>
			<c:import url="includes/header.jsp" charEncoding="UTF-8"/>
			-->
			<c:forEach items="${nameList}" var="name" varStatus="status">
				<c:set var="name" value="${name}" scope="request"/>
				<c:import url="includes/company-article.jsp" charEncoding="UTF-8"/>
			</c:forEach>
			<!-- Footer -->
			<c:import url="includes/job-footer.jsp" charEncoding="UTF-8"/>
		</main>
		<!-- Footer -->
		<c:import url="/html/footer.html" charEncoding="UTF-8"/>
	</body>
</html>