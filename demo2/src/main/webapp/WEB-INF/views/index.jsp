<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta charset="utf-8" />
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
	</head>
	<body>
		<!-- Header -->
		<c:import url="/html/header.html" charEncoding="UTF-8"/>
		<!-- Main -->

		<main id="main">
			<c:import url="/html/category.html" charEncoding="UTF-8"/>
		</main>
		<!-- Footer -->
		<c:import url="/html/footer.html" charEncoding="UTF-8"/>
	</body>
</html>