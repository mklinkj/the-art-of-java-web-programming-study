<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>

<c:set var="id" value="hong" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${param.name}" scope="page" />
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page"/>


<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원 정보 출력창</title>
  <style>
    table {
      margin-left: auto;
      margin-right: auto;
      width: 100%;
    }

    table, th, td {
      border: 1px solid black;
    }

    th {
      background-color: #99ccff;
    }

    td {
      text-align: center;
    }

    .th-id {
      width: 7%;
    }
    .th-pw {
      width: 7%;
    }
    .th-name {
      width: 7%;
    }
    .th-age {
      width: 7%;
    }
    .th-height {
      width: 7%;
    }
  </style>
</head>
<body>
<table>
  <tbody>
  <tr>
    <th class="th-id">아이디</th>
    <th class="th-pw">비밀번호</th>
    <th class="th-name">이름</th>
    <th class="th-age">나이</th>
    <th class="th-height">키</th>
  </tr>
  <c:choose>
    <c:when test="${empty name}">
      <tr>
        <td colspan="5">이름을 입력하세요!!</td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td>${id}</td>
        <td>${pwd}</td>
        <td>${name}</td>
        <td>${age}</td>
        <td>${height}</td>
      </tr>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>
<button type="button" onclick="location.href='../index.html'">이전으로 돌아가기 ... </button>
</body>
</html>
