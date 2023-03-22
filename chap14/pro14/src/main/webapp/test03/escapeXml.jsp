<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>escapeXml 변환하기</title>
</head>
<body>
  <h2>escapeXml 변환하기</h2>
  <pre>
    <c:out value="&lt;" escapeXml="true" />
    <c:out value="&lt;" escapeXml="false" />

    <c:out value="&gt;" escapeXml="true" />
    <c:out value="&gt;" escapeXml="false" />

    <c:out value="&amp;" escapeXml="true" />
    <c:out value="&amp;" escapeXml="false" />

    <c:out value="&#039;" escapeXml="true" />
    <c:out value="&#039;" escapeXml="false" />

    <c:out value="&#034;" escapeXml="true" />
    <c:out value="&#034;" escapeXml="false" />
  </pre>



<button type="button" onclick="location.href='../index.html'">이전으로 돌아가기 ... </button>
</body>
</html>
