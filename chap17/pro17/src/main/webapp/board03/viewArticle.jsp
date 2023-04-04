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
  <title>게시물 보기</title>
  <style>
    /* 부트스트랩 레이어 위치 해깔릴 때 잠깐씩 켜서 확인 해보자! */
    .box {
      border: 1px solid black;
    }
  </style>
</head>
<body>
<h1 class="text-center mt-4 mb-4">게시물 보기</h1>

<div class="card d-flex justify-content-center mx-auto col-9">
  <div class="card-body ">
    <form id="articleForm" method="post" enctype="multipart/form-data">
      <div class="row mb-3">
        <label for="inputArticleNo" class="col-sm-2 col-form-label text-center">글 번호</label>
        <div class="col-sm-10">
          <input type="text" name="articleNo" class="form-control-plaintext" id="inputArticleNo"
                 value="${article.articleNo}" readonly>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputId" class="col-sm-2 col-form-label text-center">작성자 ID</label>
        <div class="col-sm-10">
          <input type="text" name="id" class="form-control-plaintext" id="inputId"
                 value="${article.id}" readonly>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputTitle3" class="col-sm-2 col-form-label text-center">글 제목</label>
        <div class="col-sm-10">
          <input type="text" name="title" class="form-control" id="inputTitle3"
                 value="${article.title}" disabled>
        </div>
      </div>

      <div class="row mb-3">
        <label for="inputContent3" class="col-sm-2 col-form-label text-center align-text-top">글
          내용</label>
        <div class="col-sm-10">
          <textarea class="form-control" rows="10" maxlength="4000" name="content"
                    id="inputContent3" disabled>${article.content}</textarea>
        </div>
      </div>


      <div class="row mb-3">
        <div class="col-2 text-center">
          <label for="preview" class="col col-form-label">이미지</label>
        </div>
        <div class="col row-cols">
          <div class="col">
            <div id="preview_div" class="ro mb-3 ">
              <c:choose>
                <c:when
                    test="${not empty article.imageFileName and article.imageFileName ne 'null'}">
                  <img id="preview"
                       src="${contextPath}/download.do?articleNo=${article.articleNo}&imageFileName=${article.imageFileName}"
                       class="col-5 img-thumbnail rounded mx-auto d-block">
                </c:when>
                <c:otherwise>
                  <img id="preview" src=""
                       class="col-5 img-thumbnail rounded mx-auto d-block d-none">
                </c:otherwise>
              </c:choose>
            </div>
          </div>
          <div class="col">
            <div class="col-sm-10">
              <input type="hidden" name="originalFileName" value="${article.imageFileName}">
              <input type="file" name="imageFileName" class="form-control" id="inputImageFile"
                     onchange="readURL(this)" disabled>
            </div>
          </div>
        </div>
      </div>

      <div class="row mb-3">
        <label for="inputWriteDate" class="col-sm-2 col-form-label text-center">등록일자</label>
        <div class="col-sm-10">
          <input type="text" class="form-control-plaintext" id="inputWriteDate"
                 value="<javatime:format value="${article.writeDate}" pattern="yyyy-MM-dd" />">
        </div>
      </div>

      <div id="modify_process_btns" class="d-flex justify-content-center d-none">
        <button type="button" class="btn btn-warning me-2" onclick="modifyProcess(this)">수정 반영하기</button>
        <button type="button" class="btn btn-secondary me-2" onclick="modifyView(false)">취소하기
        </button>
      </div>

      <div id="modify_view_btns" class="d-flex justify-content-center">
        <button type="button" class="btn btn-outline-primary me-2" onclick="modifyView(true)">수정하기
        </button>
        <a href="${contextPath}/board4/listArticles.do">
          <button type="button" class="btn btn-secondary me-2">리스트로 돌아가기</button>
        </a>
        <button type="button" class="btn btn-danger me-2" onclick="removeProcess(this)">삭제하기</button>
        <button type="button" class="btn btn-dark">답글 쓰기</button>
      </div>
    </form>
  </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${contextPath}/webjars/jquery/@jqueryVersion@/jquery.slim.min.js"></script>
<script
    src="${contextPath}/webjars/bootstrap/@bootstrapVersion@/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
  function modifyView(show) {
    const articleForm = document.querySelector("#articleForm");
    if (show) {
      articleForm.title.disabled = false;
      articleForm.content.disabled = false;
      articleForm.querySelector("#inputImageFile").disabled = false;
      articleForm.querySelector("#modify_view_btns").classList.add("d-none");
      articleForm.querySelector("#modify_process_btns").classList.remove("d-none");
    } else {
      articleForm.title.disabled = true;
      articleForm.content.disabled = true;
      articleForm.querySelector("#inputImageFile").disabled = true;
      articleForm.querySelector("#modify_view_btns").classList.remove("d-none");
      articleForm.querySelector("#modify_process_btns").classList.add("d-none");

      if ('${article.imageFileName}' === '') {
        $('#preview_div').removeClass('d-none');
        $('#preview').attr('src', '');
        $('#preview').addClass('d-none');
        articleForm.querySelector("#inputImageFile").value = '';
      }

    }
  }

  function modifyProcess(obj) {
    obj.disabled = true;
    const articleForm = document.querySelector("#articleForm");
    articleForm.action = '${contextPath}/board4/modArticle.do'
    articleForm.submit();
  }

  function removeProcess(obj) {
    obj.disabled = true;
    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "${contextPath}/board4/removeArticle.do");

    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name","articleNo");
    input.setAttribute("value", obj.form.articleNo.value);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
  }

  function readURL(input) {
    if (input.files && input.files[0]) {
      $('#preview').removeClass('d-none');
      const reader = new FileReader();
      reader.onload = function (e) {
        $('#preview').attr('src', e.target.result);
      }
      reader.readAsDataURL(input.files[0]);
      $('#preview_div').removeClass('d-none');
    }
  }
</script>

</body>
</html>