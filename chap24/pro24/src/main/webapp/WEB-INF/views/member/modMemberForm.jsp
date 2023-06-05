<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <title>회원 정보 수정</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">회원 정보 수정</h1>

<div class="card d-flex justify-content-center mx-auto col-6">
  <div class="card-body ">
    <form:form modelAttribute="member" method="post" action="${contextPath}/member/modMember.do">
      <div class="row mb-3">
        <label for="inputId3" class="col-sm-3 col-form-label">아이디</label>
        <div class="col-sm-8">
          <input type="text" path="id" class="form-control bg-white" value="${member.id}" id="inputId3" disabled>
        </div>
      </div>
      <form:hidden path="id"/>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-3 col-form-label">비밀번호</label>
        <div class="col-sm-8">
          <form:input type="password" path="pwd" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputPassword3"/>
          <form:errors cssClass="text-danger" path="pwd"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputName3" class="col-sm-3 col-form-label">이름</label>
        <div class="col-sm-8">
          <form:input type="text" path="name" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputName3"/>
          <form:errors cssClass="text-danger" path="name"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-3 col-form-label">이메일</label>
        <div class="col-sm-8">
          <form:input type="email" path="email" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputEmail3"/>
          <form:errors cssClass="text-danger" path="email"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputJoinDate3" class="col-sm-3 col-form-label">가입일</label>
        <div class="col-sm-8">
          <form:input type="text" path="joinDate" class="form-control form-control-plaintext" id="inputJoinDate3"
                 value="${member.joinDate}" readonly="true" />
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <form:button type="submit" class="btn btn-outline-primary me-2">수정하기</form:button>
        <form:button type="reset" class="btn btn-secondary me-2">다시입력</form:button>
        <a href="listMembers.do">
          <button type="button" class="btn btn-outline-success">목록으로</button>
        </a>
      </div>
    </form:form>
  </div>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>
<script>
  if('${result}') {
    alert('<fmt:message key="modify.${result}"/>');
  }
</script>
</body>
</html>