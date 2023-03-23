<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING" %>
<%@ page import="org.mklinkj.taojwp.sec02.ex01.MemberDAO" %>
<%@ page import="org.mklinkj.taojwp.sec02.ex01.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%
  request.setCharacterEncoding(SERVER_ENCODING);
%>
<jsp:useBean  id="m" class="org.mklinkj.taojwp.sec02.ex01.MemberBean" />
<jsp:setProperty name="m" property="*" />

<%
  final MemberBean member = m;
  final MemberDAO memberDAO = new MemberDAO();
  Optional.ofNullable(m.getId()) //
      .filter(id -> !id.isBlank()) //
      .ifPresent(c ->
          memberDAO.addMember(member)
      );
  List<MemberBean> memberList = memberDAO.listMembers();
  request.setAttribute("memberList", memberList);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>14.12 표현언어와 JSTL을 이용한 회원관리 실습 - 액션 JSP</title>
</head>
<body>
  <jsp:forward page="memberList.jsp" />
</body>
</html>
