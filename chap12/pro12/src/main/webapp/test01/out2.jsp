<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  String name = request.getParameter("name");
  String age = request.getParameter("age");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>out 내장 객체 이용해 데이터 출력하기 2</title>
</head>
<body>
<%
  if (name != null || name.isBlank()) {
%>
<h1><%=name %>, <%=age %>
</h1>
<%
} else {
%>
<h1>이름을 입력하세요</h1>
<%
  }
%>

<%
  if (name != null || name.isBlank()) {
%>
<h1><% out.println(name + ", " + age); %></h1>
<%
} else {
%>
<h1>이름을 입력하세요</h1>
<%
  }
%>
<a href="/pro12/index.html">인덱스 페이지로 이동...</a>
</body>
</html>
