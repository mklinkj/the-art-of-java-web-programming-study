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
  <title>새글 쓰기</title>
</head>
<body>
<h1 class="text-center mt-4 mb-4">새글 쓰기</h1>

<div class="card d-flex justify-content-center mx-auto col-9">
  <div class="card-body ">
    <form method="post" action="${contextPath}/board4/addArticle.do" enctype="multipart/form-data">
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

      <div class="row mb-3">
        <label for="inputImageFile" class="col-sm-3 col-form-label text-center">이미지 파일 첨부</label>
        <div class="col-sm-9">
          <input type="file" name="imageFileName" class="form-control" id="inputImageFile" onchange="readURL(this)">
        </div>
      </div>

      <div class="row mb-3">
        <div id="preview_div" class="ro mb-3 d-none">
          <img id="preview" src="#" class="col-5 img-thumbnail rounded mx-auto d-block" >
        </div>
      </div>

      <div class="d-flex justify-content-center">
        <button type="submit" class="btn btn-primary me-2">글 쓰기</button>
        <a href="${contextPath}/board4/listArticles.do">
         <button type="button" class="btn btn-secondary">목록 보기</button>
        </a>
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
  function readURL(input) {
   if (input.files && input.files[0]) {
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