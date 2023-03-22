<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>시험 접수 입력 페이지</title>
</head>
<body>
  <h4>액션 주소를 선택해주세요</h4>
  <ul>
    <li><input name="actionUrl" type="radio" value="scoreResult1.jsp">scoreResult1.jsp</li>
    <li><input name="actionUrl" type="radio" value="scoreResult2.jsp" checked>scoreResult2.jsp</li>
  </ul>
  <h1>시험 점수를 입력해주세요</h1>
  <form name="scoreForm" method="get" action="scoreResult1.jsp">
    시험점수: <input type="text" name="score"> <br>
            <input type="submit" value="학점변환">
  </form>
  <script>
    document.querySelector("input[type=submit]").addEventListener("click", (e) => {
      e.preventDefault()
      e.stopPropagation()
      const form = document.scoreForm;
      form.action = document.querySelector("input[name=actionUrl]:checked").value;
      form.submit();
    }, false)
  </script>
</body>
</html>