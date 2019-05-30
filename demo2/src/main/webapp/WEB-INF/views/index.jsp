
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta charset="utf-8" />
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="stylesheet" type="text/css" href="/css/index.css" />
	</head>
	<body>
		<!-- Header -->
		<c:import url="/html/header.html" charEncoding="UTF-8"/>
		<!-- Main -->

		<main id="main" class="container">
			<div class="outer">
                <div class="inner">
                    <div class="centered">
           				<c:import url="/html/index.html" charEncoding="UTF-8"/>
           			</div>
           		</div>
           	</div>
		</main>
		<!-- Footer -->
		<c:import url="/html/footer.html" charEncoding="UTF-8"/>
	</body>
</html>