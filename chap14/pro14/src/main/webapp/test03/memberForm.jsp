<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원 가입창</title>
  <style>
    .text-center {
      text-align: center;
    }
    table {
      margin-left: auto;
      margin-right: auto;
    }
    table, th, td {
      border: 0 solid black;
    }
    th {
      text-align: right;
      width: 200px;
    }
    td {
      text-align: left;
      width: 400px;
    }
    ul {
      list-style:none;
    }
    li {
      margin-left: 120px;
    }
  </style>
</head>
<body>
<form name="joinForm" method="post">
  <h1 class="text-center">회원 가입창</h1>

  <table>
    <tbody>
    <tr>
      <th>아이디</th>
      <td>
        <input type="text" name="id">
      </td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td>
        <input type="password" name="pwd">
      </td>
    </tr>
    <tr>
      <th>이름</th>
      <td>
        <input type="text" name="name">
      </td>
    </tr>
    <tr>
      <th>이메일</th>
      <td>
        <input type="email" name="email">
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <ul>
          <li><input type="button" onclick="actionSubmit('member1.jsp')" value="가입하기 (member1.jsp)"></li>
          <li><input type="button" onclick="actionSubmit('member9.jsp')" value="가입하기 (member9.jsp)"></li>
          <li><input type="reset" value="다시입력"></li>
        </ul>
      </td>
    </tr>
    </tbody>
  </table>
</form>
<script>
  function actionSubmit(action) {
    const joinForm = document.joinForm;
    joinForm.action = action;
    joinForm.submit();
  }
</script>
</body>
</html>