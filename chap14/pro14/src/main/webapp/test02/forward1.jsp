<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  request.setAttribute("id", "hong");
  request.setAttribute("pwd", "1234");
  session.setAttribute("name", "홍길동");
  application.setAttribute("email", "hong@test.com");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>forward1</title>
</head>
<body>
  <jsp:forward page="member1.jsp"></jsp:forward>
</body>
</html>
