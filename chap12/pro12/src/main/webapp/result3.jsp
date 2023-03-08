<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  String userId = request.getParameter("user_id");
  String userPw = request.getParameter("user_pw");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 결과출력 창</title>
</head>
<body>
<%
  if (userId == null || userId.isBlank()) {
%>
아이디를 입력하세요. <br>
<a href="/pro12/login.html">로그인 하기</a>
<%
} else {
    if("admin".equals(userId)) {
%>
  <h1>관리자로 로그인 했습니다.</h1>
  <form>
    <input type="button" value="회원정보 삭제하기">
    <input type="button" value="회원정보 수정하기">
  </form>
<%
  } else {
%>
<h1>환영 합니다. <%=userId%>님!!!</h1>
<%
  }
}
%>
</body>
</html>
