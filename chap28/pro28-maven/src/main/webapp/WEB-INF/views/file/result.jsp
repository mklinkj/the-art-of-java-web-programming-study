<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div>
  <h1>업로드가 완료되었습니다.</h1>
  <div class="mb-3 row">
    <label for="staticId" class="col-sm-1 col-form-label">아이디</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="staticId" name="id" value="${map.id}" readonly>
    </div>
  </div>
  <div class="mb-3 row">
    <label for="inputName" class="col-sm-1 col-form-label">이름</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="inputName" name="name" value="${map.name}" readonly>
    </div>
  </div>

  <div class="result-images ">
    <c:forEach var="imageFileName" items="${map.fileList}">
      <div class="border border-primary rounded-1 ">
        <img class="mx-auto d-block" style="max-width: 500px;" src="${contextPath}/file/download?imageFileName=${imageFileName}">
      </div>
    </c:forEach>
  </div>
  <a class="btn btn-secondary" href="${contextPath}/file/form">다시 업로드하기</a>
</div>
