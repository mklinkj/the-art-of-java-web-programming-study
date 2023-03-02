<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
  아직 IntelliJ에서는 최신 uri이름을 지원하지 않는다. 위에 것을 쓰더라도 실행에 문제는 없음.
  * https://youtrack.jetbrains.com/issue/IDEA-308542/JSTL-Jakarta-Standard-Tag-Library-3.0.0-has-a-new-namespace-and-URI-for-TLD-files-IntelliJ-does-not-recognise-this
--%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 창</title>
</head>

<body>
<h4>서블릿 URL 선택:</h4>

<h4>&lt;c:url value=""/&gt;출력값: <c:url value=""/></h4>

<ul>
  <li><input type="radio" name="servlet_name" value="sessionLogin" checked>세션 login 서블릿</li>
</ul>
<h4>요청방식 선택:</h4>
<ul>
  <li><input type="radio" name="request_method" value="POST" checked>POST 방식</li>
  <li><input type="radio" name="request_method" value="GET">GET 방식</li>
</ul>

<form name="frmLogin" method="post">
  아이디: <input type="text" name="user_id"><br>
  비밀번호: <input type="text" name="user_pw"><br>
  <input type="hidden" name="login_url" value="login2jsp.jsp">
  <input type="submit" value="로그인">
  <input type="reset" value="다시 입력">
</form>
<script>
  const formLogin = document.frmLogin;
  formLogin.addEventListener('submit',
      () => {
        const servletName = document.querySelector("input[name='servlet_name']:checked").value
        formLogin.action = servletName;

        const method = document.querySelector("input[name='request_method']:checked").value
        formLogin.method = method;

        formLogin.submit();
      }
  )
</script>
</body>
</html>