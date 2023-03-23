<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
request.setCharacterEncoding(SERVER_ENCODING);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>JSTL 다국어 기능</title>
</head>
<body>
<fmt:setLocale value="${param.lang}" />
<h1>
  회원정보<br><br>
  <fmt:bundle basename="messages.member" prefix="mem." >
    이름: <fmt:message key="name" /><br>
    주소: <fmt:message key="address" /><br>
    직업: <fmt:message key="job" />
  </fmt:bundle>
</h1>
</body>
</html>
