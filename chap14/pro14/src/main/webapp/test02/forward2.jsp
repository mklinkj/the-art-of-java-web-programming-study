<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="org.mklinkj.taojwp.sec01.ex01.MemberBean" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  MemberBean member = new MemberBean("lee", "1234", "이순신", "lee@test.com");
  request.setAttribute("member", member);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>forward2</title>
</head>
<body>
  <jsp:forward page="member2.jsp"></jsp:forward>
</body>
</html>
