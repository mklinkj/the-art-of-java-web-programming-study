<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>표현 언어에서 사용되는 데이터들</title>
</head>
<body>
  <h1>표현 언어에서 사용되는 데이터들</h1>
  <h1>
    \${100}: ${100 + 10}<br>
    \${"안녕하세요"}: ${"안녕하세요"}<br>
    \${10 + 1}: ${10 + 1}<br>
    \${"10" + 1}: ${"10" + 1}<br>
    \${null + 1}: ${null + 10}<br>
    <%-- \${"안녕" + 11}: ${"안녕" + 11}<br>--%> <%-- 숫자로 암묵적 형변환 할 수 없는 문자열과 숫자는 더할 수 없음 --%>
    <%-- \${"hello" + "world"}: ${"hello" + "world"} --%> <%-- 표현식 내에서 문자열 끼리도 더 할 수 없음--%>
  </h1>
</body>
</html>
