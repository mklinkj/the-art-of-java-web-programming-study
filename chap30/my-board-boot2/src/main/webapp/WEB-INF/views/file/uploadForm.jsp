<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div>
  <h1>파일 업로드 하기</h1>

  <form method="post" action="${contextPath}/file/upload" enctype="multipart/form-data">
    <div class="mb-3 row">
      <label for="staticId" class="col-sm-2 col-form-label">아이디</label>
      <div class="col-sm-3">
        <input type="text" class="form-control" id="staticId" name="id" placeholder="아이디 입력">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="inputName" class="col-sm-2 col-form-label">이름</label>
      <div class="col-sm-3">
        <input type="text" class="form-control" id="inputName" name="name" placeholder="이름 입력">
      </div>
    </div>
    <sec:csrfInput/>
    <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> -->
    <div class="d-grid gap-2 d-md-block">
      <input type="button" class="btn btn-outline-secondary" value="파일추가" onclick="fn_addFile()" />
      <input type="submit" class="btn btn-outline-primary" value="업로드"/>
      </div>
    <div id="d_file">
    </div>
  </form>
  <script>
    let count = 1;

    function fn_addFile() {
      $("#d_file").append(
        `<div class="mb-3">
          <label for="formFile\${count}" class="form-label">\${count}번 파일</label>
          <input class="form-control" type="file" id="formFile\${count}" name="file\${count}">
        </div>`
      );
      count++;
    }
  </script>
</div>

