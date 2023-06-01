<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="ko">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet"
        href="${contextPath}/webjars/bootstrap/@bootstrapVersion@/css/bootstrap.min.css">
  <title>회원정보</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">회원정보</h1>

<table class="table table-bordered">
  <thead class="table-primary">
  <tr class="text-center">
    <th>아이디</th>
    <th>비밀번호</th>
    <th>이름</th>
    <th>이메일</th>
  </tr>
  </thead>
  <tbody>
  <tr class="text-center">
    <td>${id}</td>
    <td>${pwd}</td>
    <td>${name}</td>
    <td>${email}</td>
  </tr>
  </tbody>
</table>

<div class="text-center">
  <a href="${contextPath}/test/memberForm.jsp">회원 정보 입력창으로 돌아가기</a>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>
</body>
</html>