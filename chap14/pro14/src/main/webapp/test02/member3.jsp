<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>

<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>

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
      width: 5%;
    }

    .th-email {
      width: 11%;
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
    <th class="th-email">이메일</th>
  </tr>
  <tr>
    <td>${membersList[0].id}</td>
    <td>${membersList[0].pwd}</td>
    <td>${membersList[0].name}</td>
    <td>${membersList[0].email}</td>
  </tr>
  <tr>
    <td>${membersList[1].id}</td>
    <td>${membersList[1].pwd}</td>
    <td>${membersList[1].name}</td>
    <td>${membersList[1].email}</td>
  </tr>
  </tbody>
</table>
<button type="button" onclick="location.href='../index.html'">이전으로 돌아가기 ... </button>
</body>
</html>
