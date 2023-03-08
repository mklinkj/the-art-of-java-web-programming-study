<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 결과출력 창</title>
</head>
<body>
<h1>결과 출력</h1>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  String userId = request.getParameter("user_id");
  String userPw = request.getParameter("user_pw");
%>
<h1>아이디: <%= userId%></h1>
<h1>암호: <%= userPw%></h1>
</body>
</html>
