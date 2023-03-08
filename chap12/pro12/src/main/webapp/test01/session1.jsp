<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String name = (String)session.getAttribute("name");
  session.setAttribute("address", "서울시 강남구");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>내장 객체 테스트2</title>
</head>
<body>
  이름은 <%=name %>입니다.<br>
  <a href="session2.jsp">두 번째 페이지로 이동</a>
</body>
</html>
