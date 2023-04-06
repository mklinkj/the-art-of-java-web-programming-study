<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
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

<table class="table table-bordered">
  <thead class="table-primary">
    <tr class="text-center">
      <th>아이디</th>
      <th>비밀번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>가입일</th>
      <th>수정</th>
      <th>삭제</th>
    </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty memberList}">
      <tr class="text-center">
        <td colspan="7">
          <b>등록된 회원이 없습니다.</b>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="mem" items="${memberList}">
        <tr class="text-center">
          <td>${mem.id}</td>
          <td>${mem.pwd}</td>
          <td>${mem.name}</td>
          <td>${mem.email}</td>
          <td><javatime:format value="${mem.joinDate}" pattern="yyyy-MM-dd" /></td>
          <td>
            <a href="${contextPath}/member2/modMemberForm.do?id=${mem.id}">
              <button type="button" class="btn btn-warning">수정</button>
            </a>
          </td>
          <td>
            <form action="${contextPath}/member2/delMember.do" method="post">
              <input type="hidden" name="id" value="${mem.id}">
              <button type="submit" class="btn btn-danger">삭제</button>
            </a>
            </form>
          </td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<div class="text-center">
  <a href="${contextPath}/member2/memberForm.do">회원가입하기</a>
</div>

<div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">${msg}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>${msg}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary">Save changes</button>-->
      </div>
    </div>
  </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>

<script>
  // show modal
  const result = '${msg}';

  const modal = new bootstrap.Modal(document.querySelector(".modal"));

  if (result) {
    modal.show();
  }
</script>
</body>
</html>