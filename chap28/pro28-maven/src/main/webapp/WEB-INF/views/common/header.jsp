<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<nav class="navbar bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand">
      <img src="/resources/image/smile.png" alt="Logo" width="100" height="100" class="d-inline-block align-middle">
      <span class="navbar-brand mb-0 text-light">스프링 실습 홈페이지!! (Spring 5 + Tiles + Maven)</span>
    </a>

    <ul class="nav justify-content-end">
      <li class="nav-item">
      <%-- 현재 URL에 쿼리 스트링만 추가하면 좋긴한데, 간단하게 로케일 변경시에는 메인 페이지에서 바뀌도록 했다. --%>
      <c:choose>
        <c:when test="${pageContext.response.locale eq 'en'}">
          <a class="btn btn-sm btn-outline-info" href="${contextPath}/main.do?locale=ko">Korean</a>
          <a class="btn btn-sm btn-outline-info disabled">English</a>
        </c:when>
        <c:otherwise>
          <a class="btn btn-sm btn-outline-info disabled">한글</a>
          <a class="btn btn-sm btn-outline-info" href="${contextPath}/main.do?locale=en">영어</a>
        </c:otherwise>
      </c:choose>
      <sec:authorize access="isAuthenticated()">
        <form method="post" action="${contextPath}/logout">
          <span class="text-light"><sec:authentication property="principal.username"/>님 환영합니다.😀</span>
          <button type="submit" class="btn btn-sm btn-light">로그아웃</button>
          <sec:csrfInput/>
        </form>
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
        <a class="btn btn-outline-light" href="${contextPath}/member/memberForm.do">회원가입</a>
        <a class="btn btn-outline-light" href="${contextPath}/login.do">로그인</a>            
      </sec:authorize>      
    </ul>
  </div>
</nav>