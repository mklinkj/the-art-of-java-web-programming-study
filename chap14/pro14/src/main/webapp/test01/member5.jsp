<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberBean" %>

<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>

<jsp:useBean id="m1" class="org.mklinkj.taojwp.sec01.ex01.MemberBean"/>
<jsp:setProperty name="m1" property="*"/>
<jsp:useBean id="membersList" class="java.util.ArrayList" />
<jsp:useBean id="membersMap" class="java.util.HashMap" />
<%
  membersMap.put("id", "park2");
  membersMap.put("pwd", "4321");
  membersMap.put("name", "박지성");
  membersMap.put("email", "park2@test.com");
%>

<%
  MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
  membersList.add(m1);
  membersList.add(m2);
  membersMap.put("membersList", membersList);
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
    <td>${membersMap.id}</td>
    <td>${membersMap.pwd}</td>
    <td>${membersMap.name}</td>
    <td>${membersMap.email}</td>
  </tr>
  <tr>
    <td>${membersMap.membersList[0].id}</td>
    <td>${membersMap.membersList[0].pwd}</td>
    <td>${membersMap.membersList[0].name}</td>
    <td>${membersMap.membersList[0].email}</td>
  </tr>
  <tr>
    <td>${membersMap.membersList[1].id}</td>
    <td>${membersMap.membersList[1].pwd}</td>
    <td>${membersMap.membersList[1].name}</td>
    <td>${membersMap.membersList[1].email}</td>
  </tr>
  </tbody>
</table>
<button type="button" onclick="location.href='memberForm.jsp'">이전으로 돌아가기 ... </button>
</body>
</html>
