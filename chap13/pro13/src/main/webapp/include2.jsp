<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>
<head>
  <meta charset="UTF-8">
  <title>include1.jsp</title>
</head>
<body>
  안녕하세요. 쇼핑몰 중심 JSP 시작입니다!!!
  <br>
  <jsp:include page="smile_image.jsp" flush="true">
    <jsp:param name="name" value="스마일2"/>
    <jsp:param name="imgName" value="smile2.png"/>
  </jsp:include>
  <br>
  안녕하세요. 쇼핑몰 중심 JSP 끝 부분입니다!!!
</body>
</html>
