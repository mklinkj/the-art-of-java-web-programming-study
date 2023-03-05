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
    <h4>속성 설정:  <a href="<c:url value="/set" />"><c:url value="/set"/></a></h4>
    <h4>속성 조회:  <a href="<c:url value="/get" />"><c:url value="/get"/></a></h4>

  </li>
</ul>

</body>
</html>