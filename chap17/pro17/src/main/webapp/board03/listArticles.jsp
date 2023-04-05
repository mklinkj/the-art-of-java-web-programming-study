<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="totArticles" value="${articlesMap.totArticles}" />
<c:set var="section" value="${articlesMap.section}" />
<c:set var="pageNum" value="${articlesMap.pageNum}" />

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
    <c:when test="${empty articlesList}">
      <tr class="text-center">
        <td colspan="4">
          <b>등록된 글이 없습니다.</b>
        </td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="article" items="${articlesList}" varStatus="articleNum">
        <tr class="text-center">
          <td>${article.articleNo}</td>
          <td>${article.id}</td>

          <td class="text-start">
            <span class="m-1"></span>
            <c:choose>
              <c:when test="${article.level > 1}">
                <span class="m-${article.level}"></span>
                <span class="text-secondary">[답변]</span>
                <a class="text-decoration-none" href="${contextPath}/board4/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
              </c:when>
              <c:otherwise>
                <a class="text-decoration-none" href="${contextPath}/board4/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
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

<c:if test="${totArticles != null}">
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
      <c:set var="pageSize" value="${10}" /> <%-- 페이지 사이즈 --%>
      <c:set var="naviSize" value="${10}" /> <%-- 네비게이션 사이즈 --%>

      <%-- 전체 게시글 수를 페이지 사이즈로 나눈 나머지 값 --%>
      <c:set var="reminderTotal" value="${totArticles % pageSize}" />

      <%-- 전체 페이지 수에 대해 10단위로 가까운 큰값: 997이라면 1000이 설정  --%>
      <c:set var="cealTotArticles" value="${reminderTotal > 0 ? (totArticles / pageSize) * pageSize + pageSize : totArticles }" />

      <%-- 페이지 네비게이션 번호 --%>
      <c:forEach var="pageNaviNo" begin="1" end="10" step="1">
        <%-- 이전 --%>
        <c:if test="${section > 1 && pageNaviNo == 1}">
          <li class="page-item">
            <a class="page-link" href="${contextPath}/board4/listArticles.do?section=${section - 1}&pageNum=${(section - 2) * pageSize + 1}">pre</a>
          </li>
        </c:if>

        <c:if test="${cealTotArticles >= ((section - 1) * pageSize + pageNaviNo) * pageSize}">

          <li class="page-item <c:if test="${pageNum eq (section - 1) * pageSize + pageNaviNo}">active</c:if>"><a class="page-link" href="${contextPath}/board4/listArticles.do?section=${section}&pageNum=${(section - 1) * pageSize + pageNaviNo}">${(section - 1) * pageSize + pageNaviNo}</a></li>

          <c:if test="${cealTotArticles >= ((section - 1) * pageSize + pageNaviNo + 1) * pageSize}">
            <c:if test="${pageNaviNo eq naviSize}">
              <li class="page-item">
                <a class="page-link" href="${contextPath}/board4/listArticles.do?section=${section + 1}&pageNum=${section * pageSize + 1}">next</a>
              </li>
            </c:if>
          </c:if>
        </c:if>
      </c:forEach>
  </ul>
</nav>
</c:if>



<div class="text-center">
  <a class="text-decoration-none" href="${contextPath}/board4/articleForm.do">글쓰기</a>
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