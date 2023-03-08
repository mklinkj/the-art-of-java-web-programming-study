<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  int score = Integer.parseInt(request.getParameter("score"));
%>
<head>
  <title>점수 출력 창</title>
</head>
<body>
<h1>시험점수 <%=score %>점</h1>
<%
  if (score >= 90) {
%>
<h1>A학점입니다.</h1>
<%
} else if (score >= 80 && score < 90) {
%>
<h1>B학점입니다.</h1>
<%
} else if (score >= 70 && score < 80) {
%>
<h1>C학점입니다.</h1>
<%
} else if (score >= 60 && score < 70) {
%>
<h1>D학점입니다.</h1>
<%
} else {
%>
<h1>F학점입니다.</h1>
<%
  }
%>
<a href="scoreTest.html">시험점수입력</a>
</body>
</html>
