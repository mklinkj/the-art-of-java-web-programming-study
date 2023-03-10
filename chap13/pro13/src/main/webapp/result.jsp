<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:forward page="login.jsp"/>
<%
  }
%>
  <h1>환영합니다. <%=userID %>님!!</h1>
</body>
</html>
