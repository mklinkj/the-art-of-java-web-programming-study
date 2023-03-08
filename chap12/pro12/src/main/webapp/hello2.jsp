<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  String name = "이순신";

  public String getName() {
    return name;
  }
%>
<% String age = request.getParameter("age"); %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <title>스크립트릿 연습</title>
</head>
<body>
<h1>안녕하세요 <%=name %>
</h1>
<h1>나이는 <%=age %>입니다.!!</h1>
</body>
</html>
