<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING"
         import="java.util.List"
         import="org.mklinkj.taojwp.sec02.ex01.MemberVO"
         import="org.mklinkj.taojwp.sec02.ex01.MemberDAO"
         import="java.time.format.DateTimeFormatter"
         pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <style>
    h1, h4 {
      text-align: center;
    }

    th {
      background-color: #ffff66;
    }

    table {
      margin-left: auto;
      margin-right: auto;
      width: 800px;
    }

    table, th, td {
      text-align: center;
      border: 1px solid;
    }
  </style>
  <meta charset="UTF-8">
  <title>회원 정보 출력</title>
  <%
    request.setCharacterEncoding(SERVER_ENCODING);
    String _name = request.getParameter("name");
    MemberVO memberVO = new MemberVO();
    memberVO.setName(_name);
    MemberDAO dao = new MemberDAO();
    List<MemberVO> memberList = dao.listMembers(memberVO);
  %>
  <table>
    <h1>회원 정보 출력</h1>
    <tbody>
    <tr>
      <th>아이디</th>
      <th>비밀번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>가입일자</th>
    </tr>
    <%
      for (MemberVO vo : memberList) {
    %>
    <tr>
      <td><%=vo.getId() %>
      </td>
      <td><%=vo.getPwd() %>
      </td>
      <td><%=vo.getName() %>
      </td>
      <td><%=vo.getEmail() %>
      </td>
      <td><%=vo.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) %>
      </td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <h4><a href="search.jsp">검색 메뉴로 돌아가기...</a></h4>
</head>
<body>

</body>
</html>
