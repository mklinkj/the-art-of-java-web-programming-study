<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  String name = request.getParameter("name");
  String imgName = request.getParameter("imgName");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스마일 이미지</title>
</head>
<body>
<br><br>
<h1>이름은 <%=name %>입니다.</h1><br><br>
<img src="./image/<%=imgName %>"/>
</body>
</html>
