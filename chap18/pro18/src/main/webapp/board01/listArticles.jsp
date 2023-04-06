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
  <title>글목록창</title>
</head>
<body>
<table class="table table-bordered">
  <thead class="table-primary">
    <tr class="text-center">
      <th>글번호</th>
      <th>작성자</th>
      <th>제목</th>
      <th>작성일</th>
    </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty articleList}">
      <tr class="text-center">
        <td colspan="4">
          <b>등록된 글이 없습니다.</b>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="article" items="${articleList}" varStatus="articleNum">
        <tr class="text-center">
          <td>${articleNum.count}</td>
          <td>${article.id}</td>

          <td class="text-start">
            <span class="m-1"></span>
            <c:choose>
              <c:when test="${article.level > 1}">
                <span class="m-${article.level}"></span>
                <span class="text-secondary">[답변]</span>
                <a class="text-decoration-none" href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
              </c:when>
              <c:otherwise>
                <a class="text-decoration-none" href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
              </c:otherwise>
            </c:choose>
          </td>

          <td><javatime:format value="${article.writeDate}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<div class="text-center">
  <a class="text-decoration-none" href="#">글쓰기</a>
</div>

<div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">${msg.title}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>${msg.content}</p>
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
  const target = document.querySelector(".modal");
  const modal = new bootstrap.Modal(target);

  if ('${msg}') {
    modal.show();
  }
</script>

</body>
</html>