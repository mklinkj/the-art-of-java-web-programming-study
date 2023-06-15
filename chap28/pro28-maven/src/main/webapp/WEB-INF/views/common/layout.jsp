<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
  <title><tiles:insertAttribute name="title"/></title>
</head>
<body>
<div id="container" class="container min-vw-100">
  <div id="header" class="row">
    <!-- 헤더 -->
    <tiles:insertAttribute name="header"/>
  </div>
  <div class="row flex-nowrap">
    <div id="sidebar-left" class="col-sm-2">
      <!-- 왼쪽 사이드 바 -->
      <tiles:insertAttribute name="side"/>
    </div>
    <div id="content" class="col-sm" style="min-height: 500px">
      <!-- 컨텐츠 -->
      <tiles:insertAttribute name="body"/>
    </div>
  </div>
  <hr class="border border-primary border-1 opacity-75 ">

  <div id="footer" class="row">
    <!-- 푸터 -->
    <tiles:insertAttribute name="footer"/>
  </div>
</div>

<tiles:insertAttribute name="script"/>
</body>
</html>
