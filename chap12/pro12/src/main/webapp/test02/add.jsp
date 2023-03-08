<%@ page contentType="text/html;charset=UTF-8" language="java"
    errorPage="addException.jsp"
%>
<%@ page import="java.util.stream.IntStream" %>
<%
  int num = Integer.parseInt(request.getParameter("num"));
  int sum = IntStream.rangeClosed(1, num).sum();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>합계 구하기</title>
</head>
<body>
  <h2>합계 구하기</h2>
  <h1>1부터 <%=num %>까지의 합은 <%=sum %>입니다.</h1>

</body>
</html>
