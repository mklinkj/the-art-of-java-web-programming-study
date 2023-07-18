<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>

<h2 class="text-center mt-4 mb-4">답글 쓰기</h2>

<div class="card d-flex justify-content-center mx-auto col-9">
  <div class="card-body ">
    <form method="post" action="${contextPath}/board/addReply.do" enctype="multipart/form-data">
      <div class="row mb-3">
        <label for="inputParentNo" class="col-sm-2 col-form-label text-center">부모 글번호</label>
        <div class="col-sm-10">
          <input type="text" class="form-control-plaintext" id="inputParentNo" value="${parentNo}">
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputTitle3" class="col-sm-2 col-form-label text-center">글 제목</label>
        <div class="col-sm-10">
          <input type="text" name="title" class="form-control" id="inputTitle3">
        </div>
      </div>

      <div class="row mb-3">
        <label for="inputContent3" class="col-sm-2 col-form-label text-center align-text-top">글 내용</label>
        <div class="col-sm-10">
          <textarea class="form-control" rows="10" maxlength="4000" name="content" id="inputContent3"></textarea>
        </div>
      </div>

      <div id="new_file_row" class="row mb-3">
        <div class="col-2 text-center">
          <label class="col col-form-label">이미지 추가</label>
        </div>
        <div class="col">
          <div class="col-sm-10">
            <input type="button" class="btn btn-outline-secondary mb-2" value="파일추가" onclick="fn_add_file()" />
            <input type="button" class="btn btn-outline-secondary mb-2" value="초기화" onclick="fn_reset_file()" />
          </div>
          <div id="new_files_div">
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-center">
        <button type="submit" class="btn btn-primary me-2">답글 반영하기</button>
        <a href="${contextPath}/board/viewArticle.do?articleNo=${parentNo}">
         <button type="button" class="btn btn-secondary">취소</button>
        </a>
      </div>
      <sec:csrfInput/>
    </form>
  </div>
</div>


<script src="${contextPath}/resources/js/attach_file_support.js"></script>
