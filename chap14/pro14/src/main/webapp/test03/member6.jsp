<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);

  List<String> dataList = new ArrayList<>();
  dataList.add("hello");
  dataList.add("world");
  dataList.add("안녕하세요!!");
%>
<c:set var="list" value="<%=dataList %>" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>반복문 실습</title>
</head>
<body>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
  i= ${i} &nbsp;&nbsp;&nbsp; 반복횟수: ${loop.count}<br>
</c:forEach>
<br>
<c:forEach var="i" begin="1" end="10" step="2">
  5 * ${i} = ${5* i}<br>
</c:forEach>
<br>
<c:forEach var="data" items="${list}">
  ${data}<br>
</c:forEach>
<br>
<c:set var="fruits" value="사과,파인애플,바나나 망고,귤"/>
<c:forTokens items="${fruits}" delims="," var="token">
  ${token}<br>
</c:forTokens>

<br>

<button type="button" onclick="location.href='../index.html'">이전으로 돌아가기 ... </button>
</body>
</html>
