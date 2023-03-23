<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원 목록 창</title>
  <style>
    table {
      margin-left: auto;
      margin-right: auto;
      width: 100%;
    }

    table, th, td {
      border: 0px solid black;
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
    .th-email {
      width: 7%;
    }
    .th-join-date {
      width: 7%;
    }

    .foot-td {
      height: 1px;
      background-color: #99ccff;
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
    <th class="th-join-date">가입일</th>
  </tr>
  <c:choose>
    <c:when test="${empty memberList}">
      <tr>
        <td colspan="4">등록된 회원이 없습니다.</td>

      </tr>

    </c:when>
    <c:otherwise>
      <c:forEach var="member" items="${memberList}">
        <tr>
          <td>${member.id}</td>
          <td>${member.pwd}</td>
          <td>${member.name}</td>
          <td>${member.email}</td>
          <td><javatime:format value="${member.joinDate}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:forEach>
    </c:otherwise>

  </c:choose>
  <tr>
    <td class="foot-td" colspan="5"></td>
  </tr>
  </tbody>
</table>
<button type="button" onclick="location.href='memberForm.jsp'">이전으로 돌아가기 ... </button>
</body>
</html>
