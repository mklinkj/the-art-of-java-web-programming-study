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
        href="${contextPath}/webjars_locator/bootstrap/css/bootstrap.min.css">
  <title>회원정보</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">회원 정보</h1>

<div class="card d-flex justify-content-center mx-auto col-6">
  <div class="card-body ">
    <div class="row mb-3">
      <label for="inputId3" class="col-sm-3 col-form-label">아이디</label>
      <div class="col-sm-8">
        <input type="text" name="id" class="form-control" id="inputId3" value="${member.id}"
               readonly>
      </div>
    </div>
    <div class="row mb-3">
      <label for="inputName3" class="col-sm-3 col-form-label">이름</label>
      <div class="col-sm-8">
        <input type="text" name="name" class="form-control" id="inputName3" value="${member.name}"
               readonly>
      </div>
    </div>
    <div class="row mb-3">
      <label for="inputEmail3" class="col-sm-3 col-form-label">이메일</label>
      <div class="col-sm-8">
        <input type="email" name="email" class="form-control" id="inputEmail3"
               value="${member.email}" readonly>
      </div>
    </div>
    <div class="row mb-3">
      <label for="inputJoinDate3" class="col-sm-3 col-form-label">가입일</label>
      <div class="col-sm-8">
        <input type="text" name="joinDate" class="form-control" id="inputJoinDate3"
               value="${member.joinDate}" readonly>
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <a href="modMemberForm.do?id=${member.id}">
        <button type="button" class="btn btn-primary me-2">수정하기</button>
      </a>
      <a href="listMembers.do">
        <button type="button" class="btn btn-outline-success">목록으로</button>
      </a>
    </div>
  </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars_locator/jquery/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>