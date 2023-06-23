<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="ko">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet"
        href="${contextPath}/webjars_locator/bootstrap/css/bootstrap.min.css">
  <title>회원정보</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4"> 로그인</h1>

<div class="card d-flex justify-content-center mx-auto col-6">
  <div class="card-body ">

    <c:if test="${param.error}" >
      <div class="alert alert-danger" role="alert">
        <spring:message code="login.failure"/>
      </div>
    </c:if>

    <form method="post" action="${contextPath}/login">
      <div class="row mb-3">
        <label for="inputId3" class="col-sm-3 col-form-label">아이디</label>
        <div class="col-sm-8">
          <input type="text" name="username" class="form-control" id="inputId3">
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-3 col-form-label">비밀번호</label>
        <div class="col-sm-8">
          <input type="password" name="password" class="form-control" id="inputPassword3">
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <button type="submit" class="btn btn-primary me-2">로그인</button>
        <a href="${contextPath}/index.html">
          <button type="button" class="btn btn-secondary">예제 메인으로</button>
        </a>
      </div>
      <sec:csrfInput/>
    </form>
  </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars_locator/jquery/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>