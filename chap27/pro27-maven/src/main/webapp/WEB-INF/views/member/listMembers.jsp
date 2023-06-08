<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<h2 class="text-center mt-4 mb-4">회원관리</h2>
<%-- 공통 로그인 헤더 // --%>
<form method="get" action="listMembers.do">

<div class="input-group w-50 mb-3">
  <label class="input-group-text" for="inputKeyword01">회원 검색</label>
  <input class="form-control w-25" name="keyword" id="inputKeyword01" value="${searchDTO.keyword}" placeholder="검색어를 입력해주세요...">
  <select class="form-select" name="type">
    <c:forEach var="type" items="${searchTypes}">
      <option value="${type.name}" <c:if
          test="${type.name eq searchDTO.type.name}">selected</c:if>>${type.description}</option>
    </c:forEach>
  </select>
  <button class="btn btn-outline-secondary" type="submit">검색</button>
</div>
</form>

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
          <td>
            <span class="d-inline-block text-truncate" style="max-width: 150px">
              ${mem.pwd}
            </span>
          </td>
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

</div>

<script>
  if ('${result}') {
    alert('<fmt:message key="list.${result}"/>');
  }
</script>