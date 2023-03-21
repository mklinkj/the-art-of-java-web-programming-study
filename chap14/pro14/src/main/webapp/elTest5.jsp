<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="m1" class="org.mklinkj.taojwp.sec01.ex01.MemberBean" scope="page"/>
<jsp:setProperty name="m1" property="name" value="이순신"/>
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <title>14.1.6 표현 언어의 empty 연산자</title>
</head>
<body>
<h1>여러 가지 연산자들</h1>
<h1>empty 연산자</h1>
<h2>
  \${empty m1} : ${empty m1}<br>
  \${not empty m1} : ${not empty m1}<br><br>

  \${empty m2} : ${empty m2}<br>
  \${not empty m2} : ${not empty m2}<br><br>

  \${empty "hello"} : ${empty "hello"}<br>
  \${empty null} : ${empty null}<br>
  \${empty ""} : ${empty ""}<br>

</h2>
</body>
</html>
