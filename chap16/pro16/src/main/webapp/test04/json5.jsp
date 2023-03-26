<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Ajax 이용해 서버와 JSON 데이터 주고 받기</title>
  <script src="@contextPath@/webjars/jquery/@jqueryVersion@/jquery.min.js"></script>
</head>
<body>
<script>
  $(function () {
    $("#checkJson").click(function () {
      const jsonInfoStr = JSON.stringify({"name": "박지성", "age": 25, "gender": "남자", "nickname": "날센돌이"});
      $.ajax({
        type: "post",
        async: false,
        url: "@contextPath@/json",
        data: {"jsonInfo": jsonInfoStr},
        dataType: "json",
        success: function (jsonData, textStatus) {
          let output = `회원 정보<br>`;
          output += `======<br>`;
          output += `이름: \${jsonData.name}<br>`;
          output += `나이: \${jsonData.age}<br>`;
          output += `성별: \${jsonData.gender}<br>`;
          output += `별명: \${jsonData.nickname}<br><br><br>`;
          $("#output").html(output);
        }
      });

    });
  });
</script>
<button id="checkJson" type="button">출력</button>
<div id="output">
</div>
</body>
</html>
