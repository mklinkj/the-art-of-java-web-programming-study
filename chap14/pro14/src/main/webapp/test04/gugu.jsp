<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>구구단 출력 예제</title>
</head>
<body>
  <h4>액션 주소를 선택해주세요</h4>
  <ul>
    <li><input name="actionUrl" type="radio" value="guguResult1.jsp">guguResult1.jsp</li>
    <li><input name="actionUrl" type="radio" value="guguResult2.jsp" checked>guguResult2.jsp</li>
  </ul>
  <h1>출력할 구구단의 수를 지정해 주세요.</h1>
  <form name="guguForm" method="get" action="guguResult1.jsp">
    출력할 구구단 : <input type="text" name="dan"><br>
                 <input type="submit" value="출력하기">
  </form>
  <script>
    document.querySelector("input[type=submit]").addEventListener("click", (e) => {
      e.preventDefault()
      e.stopPropagation()
      const form = document.guguForm
      form.action = document.querySelector("input[name=actionUrl]:checked").value
      form.submit()
    }, false)
  </script>
</body>
</html>