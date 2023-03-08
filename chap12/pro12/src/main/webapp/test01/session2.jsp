<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String name = (String)session.getAttribute("name");
  String address = (String)session.getAttribute("address");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>내장 객체 테스트3</title>
</head>
<body>
  이름은 <%=name %>입니다.<br>
  주소는 <%=address %>입니다.<br>
  <a href="/pro12/index.html">인덱스로 이동</a>
</body>
</html>
