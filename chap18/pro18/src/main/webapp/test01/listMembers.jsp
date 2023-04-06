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
<h1 class="text-center mt-2 mb-4">회원정보</h1>

<table class="table table-bordered">
  <thead class="table-primary">
    <tr class="text-center">
      <th>아이디</th>
      <th>비밀번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>가입일</th>
    </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty memberList}">
      <tr class="text-center">
        <td colspan="5">
          <b>등록된 회원이 없습니다.</b>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="mem" items="${memberList}">
        <tr class="text-center">
          <td>${mem.id}</td>
          <td>${mem.pwd}</td>
          <td>${mem.name}</td>
          <td>${mem.email}</td>
          <td><javatime:format value="${mem.joinDate}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>
</body>
</html>