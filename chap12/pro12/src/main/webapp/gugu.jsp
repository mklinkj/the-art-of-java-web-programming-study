<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
  int dan = Integer.parseInt(request.getParameter("dan"));
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>구구단 <%=dan%>단 출력</title>
</head>
<style>
  table {
    width: 100%;
  }

  th {
    background-color: #ffff66;
  }

  table, th, td {
    text-align: center;
    border: 1px solid black;
  }
</style>
<body>
<table>
  <tbody>
  <tr>
    <th colspan="2"><%=dan%>단 출력</th>
  </tr>
  <%
    for (int i = 1; i < 10; i++) {
      if (i % 2 == 1) {
  %>
  <tr style="background-color: #ccff66">
      <%
      } else {
    %>
  <tr style="background-color: #ccccff">
    <%
      }
    %>
    <td style="width: 50%"><%=dan%> * <%=i%>
    </td>
    <td><%=dan * i%>
    </td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
<a href="gugu.html">단수 입력화면으로 돌아기기...</a>
</body>
</html>
