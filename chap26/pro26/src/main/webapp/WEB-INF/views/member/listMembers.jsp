<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="ko">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet"
        href="${contextPath}/webjars/bootstrap/@bootstrapVersion@/css/bootstrap.min.css">
  <title>회원정보</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">회원정보</h1>

<%-- // 공통 로그인 헤더 : 나중에 공통 JSP 로 분리하자 --%>
<div>
  <sec:authorize access="isAuthenticated()">
    <form method="post" action="${contextPath}/logout">
      <span><sec:authentication property="principal.username"/>님 환영합니다.😀</span>
      <button type="submit" class="btn btn-sm btn-outline-dark">로그아웃</button>
      <sec:csrfInput/>
    </form>
  </sec:authorize>
  <sec:authorize access="isAnonymous()">
    <a href="${contextPath}/member/memberForm.do">회원가입</a>
    <a href="${contextPath}/login.do">로그인</a>
  </sec:authorize>
</div>
<%-- 공통 로그인 헤더 // --%>
<div>
  <form method="get" action="listMembers.do">
    <input name="keyword" value="${searchDTO.keyword}">
    <select name="type">
      <c:forEach var="type" items="${searchTypes}">
        <option value="${type.name}" <c:if
            test="${type.name eq searchDTO.type.name}">selected</c:if>>${type.description}</option>
      </c:forEach>
    </select>
    <button type="submit">검색</button>
  </form>
</div>

<table class="table table-bordered">
  <thead class="table-primary">
  <tr class="text-center">
    <th>아이디</th>
    <th>비밀번호</th>
    <th>이름</th>
    <th>이메일</th>
    <th>가입일</th>
    <th>삭제</th>
  </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty memberList}">
      <tr class="text-center">
        <td colspan="6">
          <b>등록된 회원이 없습니다.</b>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="mem" items="${memberList}">
        <tr class="text-center">
          <td><a class="text-decoration-none" href="memberDetail.do?id=${mem.id}">${mem.id}</a></td>
          <td>${mem.pwd}</td>
          <td>${mem.name}</td>
          <td>${mem.email}</td>
          <td><javatime:format value="${mem.joinDate}" pattern="yyyy-MM-dd"/></td>
          <td>
            <form id="deleteForm_${mem.id}" action="${contextPath}/member/delMember.do"
                  method="post">
              <input type="hidden" name="id" value="${mem.id}">
              <sec:csrfInput/>
              <button type="submit" class="btn btn-sm btn-outline-danger">삭제</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<div class="text-center">
  <a href="${contextPath}/member/memberForm.do">회원가입하기</a>
  <a href="${contextPath}/index.html">예제 메인으로...</a>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>
<script>
  if ('${result}') {
    alert('<fmt:message key="list.${result}"/>');
  }
</script>

</body>
</html>