<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    
        <%
    // 0: 인증 가능 사용자 및 비밀번호 목록.
    
    request.setCharacterEncoding("utf-8");
        
    String[] users = {"ninja", "knight", "111"};
    String[] passwords = {"ak74", "m4", "111"};
    // 1: form 으로부터 전달된 데이터를 변수에 저장.
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    // 2: 사용자 인증
    String redirectUrl = "login.jsp?error=login-failed.."; // 인증 실패시 요청 될 url 
    for (int i = 0; i < users.length; i++) {
        if (users[i].equals(id) && passwords[i].equals(pw))
            redirectUrl = "welcome.jsp?id=" + id; // 인증 성공 시 재 요청 url
    }
    response.sendRedirect(redirectUrl);
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<body>

</body>
</html>