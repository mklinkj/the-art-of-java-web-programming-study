<%--
  템플릿이 완성되었을 때의 모양을 미리 만들어보자.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet"
        href="${contextPath}/webjars_locator/bootstrap/css/bootstrap.min.css">
  <title><%--<tiles:insertAttribute name="title"/>--%></title>
</head>
<body>
<div id="container" class="container min-vw-100">
  <div id="header" class="row">
    <!-- 헤더 -->
    <%--<tiles:insertAttribute name="header"/>--%>
      <nav class="navbar bg-primary">
        <div class="container-fluid">
          <a class="navbar-brand">
            <img src="/resources/image/smile.png" alt="Logo" width="100" height="100" class="d-inline-block align-middle">
            <span class="navbar-brand mb-0 text-light">스프링 실습 홈페이지!!</span>
          </a>
          <ul class="nav justify-content-end">
            <div>
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
            </div>
          </ul>
        </div>
      </nav>
    <%-- // <tiles:insertAttribute name="header"/>--%>
  </div>
  <div class="row">
    <div id="sidebar-left" class="col-sm-2">
      <%--<tiles:insertAttribute name="side"/>--%>
      <div class="nav">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><a href="${contextPath}/main.do" class="text-dark h6 text-decoration-none">메인으로...</a></li>
          <li class="list-group-item"><a href="${contextPath}/member/listMembers.do" class="text-dark h6 text-decoration-none">회원 관리</a></li>
          <li class="list-group-item"><a href="${contextPath}/index.html" class="text-dark h6 text-decoration-none">예제 메인으로...</a></li>
        </ul>
      </div>
      <%--// <tiles:insertAttribute name="side"/>--%>
    </div>
    <div id="content" class="col" style="min-height: 600px">
      <%--<tiles:insertAttribute name="body"/>--%>
      <div class="text-center">
        <h1>메인 페이지 입니다.</h1>
      </div>
      <%--// <tiles:insertAttribute name="body"/>--%>
    </div>
  </div>
  <hr class="border border-primary border-1 opacity-75 ">

  <div id="footer" class="row">
    <%--<tiles:insertAttribute name="footer"/>--%>
    <footer>
      <div class="container my-auto">
        <div class="text-center my-auto">
          <span>The Art of Java Web Programming - mklinkj - 2023</span>
        </div>
      </div>
    </footer>
    <%--//<tiles:insertAttribute name="footer"/>--%>
  </div>
</div>

<%--<tiles:insertAttribute name="script"/>--%>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars_locator/jquery/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"></script>
<%--// <tiles:insertAttribute name="script"/>--%>
</body>
</html>
