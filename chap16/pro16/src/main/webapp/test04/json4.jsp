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
      let jsonStr = '[{"name":"박지성","age":25,"gender":"남자","nickname":"날센돌이"},' +
          '{"name":"손흥민","age":30,"gender":"남자","nickname":"탱크"}]';
      let jsonObj = JSON.parse(jsonStr);
      let output = `회원 정보<br>`;
      output += `======<br>`;
      jsonObj.forEach((i)=>{
        output += `이름: \${i.name}<br>`;
        output += `나이: \${i.age}<br>`;
        output += `성별: \${i.gender}<br>`;
        output += `별명: \${i.nickname}<br><br><br>`;
      });

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
