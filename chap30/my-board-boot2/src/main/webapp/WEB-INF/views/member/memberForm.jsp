<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>

<h2 class="text-center mt-4 mb-4">회원 가입창</h2>

<div class="card d-flex justify-content-center mx-auto col-6">
  <div class="card-body ">
    <form:form method="post" action="${contextPath}/member/addMember.do">
      <div class="row mb-3">
        <label for="inputId3" class="col-sm-3 col-form-label">아이디</label>
        <div class="col-sm-8">
          <form:input type="text" path="id" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputId3"/>
          <form:errors cssClass="text-danger" path="id" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-3 col-form-label">비밀번호</label>
        <div class="col-sm-8">
          <form:input type="password" path="pwd" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputPassword3"/>
          <form:errors cssClass="text-danger" path="pwd" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputName3" class="col-sm-3 col-form-label">이름</label>
        <div class="col-sm-8">
          <form:input type="text" path="name" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputName3"/>
          <form:errors cssClass="text-danger" path="name" />
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-3 col-form-label">이메일</label>
        <div class="col-sm-8">
          <form:input type="email" path="email" cssClass="form-control"
                      cssErrorClass="form-control border-danger" id="inputEmail3"/>
          <form:errors cssClass="text-danger" path="email" />
        </div>
      </div>
      <div class="d-flex justify-content-center">
        <form:button type="submit" class="btn btn-primary me-2">가입하기</form:button>
        <form:button type="reset" class="btn btn-secondary">다시입력</form:button>
      </div>
    </form:form>
  </div>
</div>
