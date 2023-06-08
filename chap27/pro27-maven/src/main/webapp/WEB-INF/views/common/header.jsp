<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div id="container">
  <div id="header">
    <tiles:insertAttribute name="header"/>
  </div>
  <div id="sidebar-left">
    <tiles:insertAttribute name="side"/>
  </div>
  <div id="content">
    <tiles:insertAttribute name="body"/>
  </div>
  <div id="footer">
    <tiles:insertAttribute name="footer"/>
  </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars_locator/jquery/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars_locator/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
