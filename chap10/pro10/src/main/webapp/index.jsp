<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>10장 테스트 URL</title>
</head>
<body>
<ul>
  <li>
    <h3>10.1 서블릿 속성과 스코프</h3>
    <h4>속성 설정: <a href="<c:url value="/set" />">/set</a></h4>
    <h4>속성 조회: <a href="<c:url value="/get" />">/get</a></h4>
  </li>
  <li>
    <h3>10.2 서블릿 여러 가지 URL 패턴</h3>
    <h4>TestServlet1 : <a href="<c:url value="/first/test" />">/first/test</a></h4>
    <h4>TestServlet2 : <a href="<c:url value="/first/a" />">/first/a</a></h4>
    <h4>TestServlet3 : <a href="<c:url value="/a.do" />">/a.do</a></h4>
  </li>

</ul>

</body>
</html>