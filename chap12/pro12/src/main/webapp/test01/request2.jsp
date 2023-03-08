<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String name = (String) request.getAttribute("name");
  String address = (String) request.getAttribute("address");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>request 내장 객체에 데이터 바인딩 테스트2</title>
</head>
<body>
  <h1>이름은 <%=name %>입니다.</h1>
  <h1>주소는 <%=address %>입니다.</h1>
  <a href="/pro12/index.html">인덱스 페이지로 이동...</a>
</body>
</html>
