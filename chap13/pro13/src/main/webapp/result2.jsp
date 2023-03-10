<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>
<%!
  private final static String MSG = "아이디를 입력하지 않았습니다. 아이디를 입력해주세요.";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결과</title>
</head>
<body>
<%
  String userID = request.getParameter("userID");
  if(userID.isBlank()) {
%>
<jsp:forward page="login2.jsp">
  <jsp:param name="msg" value="<%=MSG %>"/>
</jsp:forward>
<%
  }
%>
  <h1>환영합니다. <%=userID %>님!!</h1>
</body>
</html>
