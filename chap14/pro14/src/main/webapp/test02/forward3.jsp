<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  List<MemberBean> membersList = new ArrayList<>();
  MemberBean m1 = new MemberBean("lee", "1234", "이순신", "lee@test.com");
  MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
  membersList.add(m1);
  membersList.add(m2);
  request.setAttribute("membersList", membersList);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>forward3</title>
</head>
<body>
  <jsp:forward page="member3.jsp"></jsp:forward>
</body>
</html>
