<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberBean" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberDAO" %>
<%@ page import="java.util.List" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>

<%--new Member()과 동일--%>
<jsp:useBean id="m" class="org.mklinkj.taojwp.sec01.ex01.MemberBean" scope="page" />

<%
  String id = request.getParameter("id");
  String pwd = request.getParameter("pwd");
  String name = request.getParameter("name");
  String email = request.getParameter("email");

  m.setId(id);
  m.setPwd(pwd);
  m.setName(name);
  m.setEmail(email);

  MemberDAO memberDAO = new MemberDAO();
  if (id != null && !id.isBlank()) {
    memberDAO.addMember(m);
  }
  List<MemberBean> memberList = memberDAO.listMembers();
%>
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
      width: 5%;
    }

    .th-email {
      width: 11%;
    }

    .th-jd {
      width: 5%;
    }

    .n-f-m-td {
      text-align: center;
    }

    .n-f-m-span {
      font-size: 9pt;
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
    <th class="th-jd">가입일</th>
  </tr>
  <%
    if (memberList.isEmpty()) {
  %>
  <tr>
    <td class="n-f-m-td" colspan="5"><span class="n-f-m-span">등록된 회원이 없습니다.</span></td>
  </tr>
  <%
  } else {
    for (MemberBean mBean : memberList) {
  %>
  <tr>
    <td><%=mBean.getId()%></td>
    <td><%=mBean.getPwd()%></td>
    <td><%=mBean.getName()%></td>
    <td><%=mBean.getEmail()%></td>
    <td><%=mBean.getJoinDate().toLocalDate()%></td>
  </tr>
  <%
      }
    }
  %>
  <tr>
    <td class="foot-td" colspan="5"></td>
  </tr>
  </tbody>
</table>
<button type="button" onclick="location.href='memberForm.html'">이전으로 돌아가기 ... </button>
</body>
</html>
