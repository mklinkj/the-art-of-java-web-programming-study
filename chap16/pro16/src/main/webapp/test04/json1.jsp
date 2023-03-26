<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>JSON의 자료형 실습</title>
  <script src="@contextPath@/webjars/jquery/@jqueryVersion@/jquery.min.js"></script>
</head>
<body>
<script>
  $(function () {
    $("#checkJson").click(function () {
      let jsonStr = '{"name":["홍길동", "이순신", "임꺽정"]}';
      let jsonInfo = JSON.parse(jsonStr);
      let output = `회원 이름<br>`;
      output += `======<br>`;
      console.log(jsonInfo.name);
      jsonInfo.name.forEach(
          (i) => {
            output += `\${i}<br>`;
          }
      );
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
