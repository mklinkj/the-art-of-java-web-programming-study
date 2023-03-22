<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>14.1.4 표현 언어의 비교 연산자</title>
</head>
<body>
  <h1>여러 가지 비교 연산자</h1>
  <h1>
    \${10 == 10}: ${10 == 10}<br>
    \${10 eq 10}: ${10 eq 10}<br><br>

    \${"hello" == "hello"}: ${"hello" == "hello"}<br>
    \${"hello" eq "hello"}: ${"hello" eq "hello"}<br><br>

    \${20 != 10}: ${20 != 10}<br>
    \${20 ne 10}: ${20 ne 10}<br><br>

    \${"hello" != "apple"}: ${"hello" != "apple"}<br>
    \${"hello" ne "apple"}: ${"hello" ne "apple"}<br><br>

    \${10 < 10}: ${10 < 10}<br>
    \${10 lt 10}: ${10 lt 10}<br><br>

    \${100 > 10}: ${100 > 10}<br>
    \${100 gt 10}: ${100 gt 10}<br><br>

    \${100 <= 10}: ${100 <= 10}<br>
    \${100 le 10}: ${100 le 10}<br><br>

    \${100 >= 10}: ${100 >= 10}<br>
    \${100 ge 10}: ${100 ge 10}<br><br>

  </h1>
</body>
</html>
