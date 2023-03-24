<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <c:set var="file1" value="${param.param1}" />
  <c:set var="file2" value="${param.param2}" />
  <title>이미지 파일 출력하기</title>
</head>
<body>
  매개변수 1:
  <c:out value="${file1}" /><br>
  매개변수 2:
  <c:out value="${file2}" /><br>

  <c:if test="${not empty file1}">
    <img src="${contextPath}/download.do?fileName=${file1}" width="460" height="460" /><br>
  </c:if>
  <br>
  <c:if test="${not empty file2}">
    <img src="${contextPath}/download.do?fileName=${file2}" width="460" height="460" /><br>
  </c:if>
  파일 내려받기: <br>
  <a href="${contextPath}/download.do?fileName=${file2}">파일 내려받기</a><br><br>

  <a href="${contextPath}/index.html">처음으로 돌아가기...</a>
</body>
</html>
