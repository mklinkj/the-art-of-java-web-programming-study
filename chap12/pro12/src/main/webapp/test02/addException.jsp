<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true"
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>에러 페이지</title>
</head>
<body>
<h3>===== toString() 내용 =====</h3>
<textarea cols="100" rows="2" readonly><%=exception.toString() %></textarea>
<h3>===== getMessage() 내용 =====</h3>
<textarea cols="100" rows="2" readonly><%=exception.getMessage() %></textarea>
<h3>===== printStackTrace() 내용 =====</h3>
<label>
<textarea cols="100" rows="35" readonly><%=
    Arrays.stream(exception.getStackTrace())
        .map(StackTraceElement::toString)
        .collect(Collectors.joining(String.format("%n    ")))
%></textarea>
</label>
<h3>숫자만 입력 가능합니다. <a href="add.html">다시 입력하기...</a></h3>
</body>
</html>
