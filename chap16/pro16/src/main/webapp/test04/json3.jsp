<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>JSON의 자료형 실습 - 정수 자료형</title>
  <script src="@contextPath@/webjars/jquery/@jqueryVersion@/jquery.min.js"></script>
</head>
<body>
<script>
  $(function () {
    $("#checkJson").click(function () {
      let jsonStr = '{"name":"박지성","age":25,"gender":"남자","nickname":"날센돌이"}';
      let jsonObj = JSON.parse(jsonStr);
      let output = `회원 정보<br>`;
      output += `======<br>`;
      output += `이름: \${jsonObj.name}<br>`;
      output += `나이: \${jsonObj.age}<br>`;
      output += `성별: \${jsonObj.gender}<br>`;
      output += `별명: \${jsonObj.nickname}<br>`;
      $("#output").html(output);
    });
  })
  ;
</script>
<button id="checkJson" type="button">출력</button>
<div id="output">
</div>
</body>
</html>
