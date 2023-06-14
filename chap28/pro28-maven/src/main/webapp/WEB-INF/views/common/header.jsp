<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<nav class="navbar bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand">
      <img src="/resources/image/smile.png" alt="Logo" width="100" height="100" class="d-inline-block align-middle">
      <span class="navbar-brand mb-0 text-light">스프링 실습 홈페이지!! (Spring 5 + Tiles + Maven)</span>
    </a>

    <ul class="nav justify-content-end">
      <sec:authorize access="isAuthenticated()">
        <li class="nav-item">
          <form method="post" action="${contextPath}/logout">
            <span class="text-light"><sec:authentication property="principal.username"/>님 환영합니다.😀</span>
            <button type="submit" class="btn btn-sm btn-light">로그아웃</button>
            <sec:csrfInput/>
          </form>
        </li>
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
        <li class="nav-item">
          <a class="btn btn-outline-light" href="${contextPath}/member/memberForm.do">회원가입</a>
          <a class="btn btn-outline-light" href="${contextPath}/login.do">로그인</a>
        </li>
      </sec:authorize>
    </ul>
  </div>
</nav>