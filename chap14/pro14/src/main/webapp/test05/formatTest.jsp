<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZonedDateTime" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>포매팅 태그 라이브러리 예제</title>
</head>
<body>
  <h2>fmt의 number 태그를 이용한 숫자 포매팅 예제</h2>
  <c:set var="price" value="10000000"/>
  <fmt:formatNumber value="${price}" type="number" var="priceNumber" /> <%-- 이부분은 왜있는지? 모르겠다. --%>
  통화로 표현 시:
  <fmt:formatNumber type="currency" currencySymbol="₩" value="${price}" groupingUsed="true" /><br>
  퍼센트로 표현 시:
  <fmt:formatNumber type="percent" value="${price}" groupingUsed="false" /><br>

  <h2>formatDate 예제</h2>
  <c:set var="now" value="<%=new Date() %>" />
  <fmt:formatDate value="${now}" type="date" dateStyle="full"/><br>
  <fmt:formatDate value="${now}" type="date" dateStyle="short"/><br>
  <fmt:formatDate value="${now}" type="time" /><br>
  <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /><br>
  <fmt:formatDate value="${now}" pattern="yyyy-MM-dd hh:mm:ss" /><br><br>

  한국 현재 시간:
  <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /><br>
  <fmt:timeZone value="America/New_York">
    뉴욕 현재 시간:
    <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full" /><br>
  </fmt:timeZone>
  <hr>
  <h2>java-time-jsptags 모듈 사용 예제</h2>
  <c:set var="localDateTime" value="<%=LocalDateTime.now() %>" />
  <javatime:format value="${localDateTime}" style="F-" /><br>
  <javatime:format value="${localDateTime}" style="S-" /><br>
  <javatime:format value="${localDateTime}" style="-M" /><br>
  <javatime:format value="${localDateTime}" style="FF" /><br>
  <javatime:format value="${localDateTime}" pattern="yyyy-MM-dd hh:mm:ss" /><br><br>

  <c:set var="zonedDateTime" value="<%=ZonedDateTime.now() %>" />
  한국 현재 시간:
  <javatime:format value="${zonedDateTime}" style="FF" /><br>
  뉴욕 현재 시간:
  <javatime:format value="${zonedDateTime}" style="FF" zoneId="America/New_York" /><br>
  <br><br>
</body>
</html>
