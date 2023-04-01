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
<h1 class="text-center mt-4 mb-4">회원 가입창</h1>

<div class="card d-flex justify-content-center mx-auto col-6">
  <div class="card-body ">
    <form method="post" action="${contextPath}/member/addMember.do">
      <div class="row mb-3">
        <label for="inputId3" class="col-sm-3 col-form-label">아이디</label>
        <div class="col-sm-8">
          <input type="text" name="id" class="form-control" id="inputId3">
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-3 col-form-label">비밀번호</label>
        <div class="col-sm-8">
          <input type="password" name="pwd" class="form-control" id="inputPassword3">
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputName3" class="col-sm-3 col-form-label">이름</label>
        <div class="col-sm-8">
          <input type="text" name="name" class="form-control" id="inputName3">
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-3 col-form-label">이메일</label>
        <div class="col-sm-8">
          <input type="email" name="email" class="form-control" id="inputEmail3">
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <button type="submit" class="btn btn-primary me-2">가입하기</button>
        <button type="reset" class="btn btn-secondary">다시입력</button>
      </div>
    </form>
  </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>
</body>
</html>