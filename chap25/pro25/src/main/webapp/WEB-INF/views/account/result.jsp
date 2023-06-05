<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <title>결과창</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">송금이 완료되었습니다.</h1>

<table class="table table-bordered">
  <thead class="table-primary">
  <tr class="text-center">
    <th>계좌번호</th>
    <th>고객이름</th>
    <th>잔액</th>
  </tr>
  </thead>
  <tbody>
      <c:forEach var="account" items="${list}">
        <tr class="text-center">
          <td>${account.accountNo}</td>
          <td>${account.custName}</td>
          <td>${account.balance}</td>
        </tr>
      </c:forEach>
  </tbody>
</table>

<div class="text-center">
  <a href="${contextPath}/account/sendMoney.do">송금 실행</a>
  <a href="${contextPath}/account/resetAllBalance.do">계좌 초기화</a>
  <a href="${contextPath}/index.html">예제 메인으로...</a>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>

</body>
</html>