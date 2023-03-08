<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String name = (String) session.getAttribute("name");
  String address = (String) application.getAttribute("address");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>내장 객체 스코프 테스트1</title>
</head>
<body>
  <h1>이름은 <%=name %>입니다.</h1>
  <h1>주소는 <%=address %>입니다.</h1>
  <a href="/pro12/index.html">인덱스 페이지로 이동...</a>
</body>
</html>
