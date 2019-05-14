<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>BBS List</title>
  <!-- Bootstrap -->
  <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <style>
    #container {
      width: 70%;
      margin: 0 auto;     /* ���η� �߾ӿ� ��ġ */
      padding-top: 10%;   /* �׵θ��� ���� ������ �е� ���� */
    }
     
    #list {
      text-align: center;
    }
   
    #write {
      text-align: right;
    }
     
    /* Bootstrap ���� */
    .table > thead {
      background-color: #b3c6ff;
    }
    .table > thead > tr > th {
      text-align: center;
    }
    .table-hover > tbody > tr:hover {
      background-color: #e6ecff;
    }
    .table > tbody > tr > td {
      text-align: center;
    }
    .table > tbody > tr > #title {
      text-align: left;
    }
     
    div > #paging {
      text-align: center;
    }
     
    .hit {
      animation-name: blink;
      animation-duration: 1.5s;
      animation-timing-function: ease;
      animation-iteration-count: infinite;
      /* �� �Ӽ����� �� �ٷ� ǥ���ϱ� */
      /* -webkit-animation: blink 1.5s ease infinite; */
    }
     
    /* �ִϸ��̼� ���� �����ϱ� */
    /* �ͽ��÷η� 10 �̻�, �ֽ� ��� ���������� ���� */
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
      /* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
    }
  </style>
</head>
<body>
  <div id="container">
    <div align="right">
      <!-- Login ���� -->
      <!-- jstl�� if���� else�� ��� ���� �����ؾ���. -->
      <c:if test="${id != null}">
        <!-- <%-- <%@include file="loginOk.jsp" %> --%> -->
      </c:if>
      <c:if test="${id == null}">
     <!--    <%-- <%@include file="company.jsp" %> --%>-->
      </c:if>
    </div>
   
    <div id="list">
      <b>�Խ��� (��ü ��: ${totalCount})</b>
    </div>
     
    <div id="write">
      <a href="/bbs/writeForm.bbs?pageNum=${pageNum}">�۾���</a>
    </div>
     
    <div>
      <table class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th width="10%">��ȣ</th>
            <th width="50%">����</th>
            <th width="10%">�ۼ���</th>
            <th width="20%">�ۼ���</th>
            <th width="10%">��ȸ</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles}" varStatus="status">
            <tr>
              <td>${article.articleNumber}</td>
              <td id="title">
                <c:if test="${article.depth > 0}">
                  &nbsp;&nbsp;
                </c:if>
                <a href="/bbs/content.bbs?articleNumber=${article.articleNumber}&pageNum=${pageNum}">${article.title}</a>
                <c:if test="${article.hit >= 20}">
                  <span class="hit">hit!</span>
                </c:if>
              </td>
              <td>${article.id}</td>
              <td>${article.writeDate}</td>
              <td>${article.hit}</td>
            <tr>
          </c:forEach>
        </tbody>
      </table>
       
      <!-- Paging ó�� -->
      <div id="paging">
        ${pageCode}
      </div>
    </div>
  </div>
</body>
</html>
