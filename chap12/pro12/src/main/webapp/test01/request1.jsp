<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setAttribute("name", "이순신");
  request.setAttribute("address", "서울시 성동구");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>request 내장 객체에 데이터 바인딩 테스트1</title>
</head>
<body>
<%
  RequestDispatcher dispatcher = request.getRequestDispatcher("request2.jsp");
  dispatcher.forward(request, response);
%>
</body>
</html>
