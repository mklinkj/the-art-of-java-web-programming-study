<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Ajax 이용해 서버와 JSON 데이터 주고 받기</title>
  <script src="@contextPath@/webjars/jquery/@jqueryVersion@/jquery.min.js"></script>
</head>
<body>
<button id="checkJson" type="button">회원 &amp; 책 정보 수신하기</button>
<div id="output">
</div>

<script>
  $(function () {
    $("#checkJson").click(function () {
      $.ajax({
        type: "post",
        async: false,
        url: "@contextPath@/json3",
        dataType: "json",
        success: function (jsonData, textStatus) {
          let output = `회원 정보<br>`;
          output += `======<br>`;
          jsonData.members.forEach(i => {
            output += `이름: \${i.name}<br>`;
            output += `나이: \${i.age}<br>`;
            output += `성별: \${i.gender}<br>`;
            output += `별명: \${i.nickname}<br><br><br>`;
          });

          output += `도서 정보<br>`;
          output += `======<br>`;
          jsonData.books.forEach(i => {
            output += `제목: \${i.title}<br>`;
            output += `저자: \${i.writer}<br>`;
            output += `가격: \${i.price}<br>`;
            output += `장르: \${i.genre}<br>`;
            output += `<img src='@contextPath@/image/\${i.image}'/><br><br><br>`;
          });
          $("#output").html(output);
        }
      });

    });
  });
</script>
</body>
</html>
