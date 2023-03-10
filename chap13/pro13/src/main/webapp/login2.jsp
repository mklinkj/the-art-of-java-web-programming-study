<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 창</title>
</head>
<body>
<%
  String msg = request.getParameter("msg");
  if (msg != null) {
%>
<h1><%=msg %>></h1>
<%
  }
%>
<form action="result.jsp" method="post">
  아이디: <input type="text" name="userID"><br>
  비밀번호: <input type="password" name="userPw"><br>
  <input type="submit" value="로그인">
  <input type="reset" value="다시입력">
</form>
</body>
</html>
