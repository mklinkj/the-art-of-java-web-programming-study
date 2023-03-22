<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>14.1.5 표현 언어의 논리 연산자</title>
</head>
<body>
<h1>여러 가지 논리 연산자</h1>
<h1>
  \${(10 == 10) && (20 == 20)} : ${(10 == 10) && (20 == 20)}<br>
  \${(10 == 10) and (20 == 20)} : ${(10 == 10) and (20 == 20)}<br><br>

  \${(10 == 10) || (20 != 30)} : ${(10 == 10) || (20 != 30)}<br>
  \${(10 != 10) or (20 != 20)} : ${(10 != 10) or (20 != 20)}<br><br>

  \${!(20 == 10)} : ${!(20 == 10)}<br>
  \${not (20 == 10)} : ${not (20 == 10)}<br><br>

  \${!(20 != 10)} : ${!(20 != 10)}<br>
  \${not (20 != 10)} : ${not (20 != 10)}<br><br>
</h1>
</body>
</html>
