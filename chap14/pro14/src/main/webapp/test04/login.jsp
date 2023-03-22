<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 창</title>
  <style>
    ul {
      list-style: none;
    }
  </style>
</head>
<body>
<h4>액션 주소를 선택해주세요</h4>
<ul>
  <li><input name="actionUrl" type="radio" value="result.jsp">result.jsp</li>
  <li><input name="actionUrl" type="radio" value="result2.jsp" checked>result2.jsp</li>
</ul>
<form name="loginForm" action="result.jsp">
  아이디: <input type="text" name="userID"><br>
  비밀번호: <input type="password" name="userPw"><br>
  <input type="submit" value="로그인"> <input type="reset" value="다시입력">
</form>
<script>
  document.querySelector("input[type=submit]").addEventListener("click", (e) => {
    e.preventDefault()
    e.stopPropagation()
    const form = document.loginForm;
    form.action = document.querySelector("input[name=actionUrl]:checked").value;
    form.submit();
  }, false)
</script>
</body>
</html>
