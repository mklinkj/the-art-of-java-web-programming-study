<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  request.setAttribute("address", "서울시 강남구");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Title</title>
</head>
<body>
  <jsp:forward page="member2.jsp"></jsp:forward>
</body>
</html>
