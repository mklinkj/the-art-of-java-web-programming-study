<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberBean" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);

  List<MemberBean> membersList = new ArrayList<>();
  MemberBean m1 = new MemberBean("son", "1234", "손흥민", "son@test.com");
  MemberBean m2 = new MemberBean("ki", "4321", "기성용", "ki@test.com");
  MemberBean m3 = new MemberBean("park", "1212", "박지성", "park@test.com");
  membersList.add(m1);
  membersList.add(m2);
  membersList.add(m3);
%>
<c:set var="membersList" value="<%=membersList %>"/>

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
      width: 5%;
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
  <c:forEach var="member" items="${membersList}">
  <tr>
    <td>${member.id}</td>
    <td>${member.pwd}</td>
    <td>${member.name}</td>
    <td>${member.email}</td>
  </tr>
  </c:forEach>
  </tbody>
</table>

<button type="button" onclick="location.href='../index.html'">이전으로 돌아가기 ... </button>
</body>
</html>
