<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="dan" value="${param.dan}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>구구단 <c:out value="${dan}"/>단 출력</title>
</head>
<style>
  table {
    width: 100%;
  }

  th {
    background-color: #ffff66;
  }

  table, th, td {
    text-align: center;
    border: 1px solid black;
  }
</style>
<body>
<table>
  <tbody>
  <tr>
    <th colspan="2"><c:out value="${dan}"/>단 출력</th>
  </tr>
  <c:forEach var="i" begin="1" end="9" step="1">
    <c:choose>
      <c:when test="${i % 2 ==0}">
        <tr style="background-color: #ccff66">
      </c:when>
      <c:otherwise>
        <tr style="background-color: #ccccff">
      </c:otherwise>
    </c:choose>

    <td style="width: 50%">
      <c:out value="${dan}"/> *
      <c:out value="${i}"/>
    </td>
    <td>
      <c:out value="${dan * i}"/>
    </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="gugu.jsp">단수 입력화면으로 돌아기기...</a>
</body>
</html>
