<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<h2 class="text-center mt-4 mb-4">게시물 보기</h2>

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

      <c:forEach var="attachFile" items="${attachFileList}" varStatus="i">
      <div class="row mb-3">
        <div class="col-2 text-center">
          <label class="col col-form-label">이미지${i.count}</label>
        </div>
        <div class="col row-cols">
          <div class="col">
            <div class="ro mb-3">
              <img src="${contextPath}/board/download.do?articleNo=${article.articleNo}&uuidFileName=${attachFile.storedFileName}"
                   class="col-5 img-thumbnail rounded mx-auto d-block">
              <div class="text-center text-primary">${attachFile.fileName}</div>
              <div class="remove_check text-center d-none">
              <label>
                <span class="text-danger">[삭제]</span>
                <input type="hidden" class="file_uuid" value="${attachFile.uuid}">
                <input type="checkbox">
              </label>
              </div>
            </div>
          </div>
        </div>
      </div>
      </c:forEach>
      <div id="new_file_row" class="row mb-3 d-none">
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
      <div class="row mb-3">
        <label for="inputWriteDate" class="col-sm-2 col-form-label text-center">등록일자</label>
        <div class="col-sm-10">
          <input type="text" class="form-control-plaintext" id="inputWriteDate"
                 value="<javatime:format value="${article.writeDate}" pattern="yyyy-MM-dd" />">
        </div>
      </div>

      <div id="modify_process_btns" class="d-flex justify-content-center d-none">
        <sec:authorize access="isAuthenticated()">
          <sec:authentication property="principal.username" var="loginUserId" />
        <c:choose>
          <c:when test="${loginUserId eq article.id}">
            <button type="button" class="btn btn-primary me-2" onclick="modifyProcess(this)">수정 반영하기</button>
          </c:when>
          <c:otherwise>
            <button type="button" class="btn btn-primary me-2 disabled">수정 반영하기</button>
          </c:otherwise>
        </c:choose>
        </sec:authorize>
        <button type="button" class="btn btn-secondary me-2" onclick="modifyView(false)">취소하기
        </button>
      </div>

      <div id="modify_view_btns" class="d-flex justify-content-center">
        <sec:authorize access="isAuthenticated()">
          <sec:authentication property="principal.username" var="loginUserId" />
        <c:choose>
          <c:when test="${loginUserId eq article.id}">
            <button type="button" class="btn btn-outline-primary me-2" onclick="modifyView(true)">수정하기</button>
            <button type="button" class="btn btn-danger me-2" onclick="removeProcess(this)">삭제하기</button>
          </c:when>
          <c:otherwise>
            <button type="button" class="btn btn-primary me-2 disabled">수정하기</button>
            <button type="button" class="btn btn-danger me-2 disabled">삭제하기</button>
          </c:otherwise>
        </c:choose>
        </sec:authorize>
        <button type="button" class="btn btn-dark me-2" onclick="replyForm(this)">답글 쓰기</button>
        <a href="${contextPath}/board/listArticles.do">
          <button type="button" class="btn btn-secondary">리스트로 돌아가기</button>
        </a>
      </div>
    </form>
  </div>
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
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">
  function modifyView(show) {
    const articleForm = document.querySelector("#articleForm");
    if (show) {
      articleForm.title.disabled = false;
      articleForm.content.disabled = false;

      articleForm.querySelector('#new_file_row').classList.remove('d-none')
      articleForm.querySelector("#modify_view_btns").classList.add('d-none');
      articleForm.querySelector("#modify_process_btns").classList.remove('d-none');
      articleForm.querySelectorAll('[class^="remove_check"]').forEach(
          el => {
            el.classList.remove("d-none")
          }
      );
    } else {
      articleForm.title.disabled = true;
      articleForm.content.disabled = true;
      articleForm.querySelector('#new_file_row').classList.add('d-none')
      articleForm.querySelector("#modify_view_btns").classList.remove("d-none");
      articleForm.querySelector("#modify_process_btns").classList.add("d-none");

      if ('${article.imageFileName}' === '') {
        $('#preview_div').removeClass('d-none');
        $('#preview').attr('src', '');
        $('#preview').addClass('d-none');

        articleForm.querySelectorAll('[class^="remove_check"]').forEach(
            el => {
              el.querySelector('input[type="checkbox"]').checked = false;
              el.classList.add("d-none");
            }
        );
      }
    }
  }

  function modifyProcess(obj) {
    obj.disabled = true;
    const articleForm = document.querySelector("#articleForm");

    const csrfInput = document.createElement("input");
    csrfInput.setAttribute("type", "hidden");
    csrfInput.setAttribute("name", $("meta[name='_csrf_parameter']").attr("content"));
    csrfInput.setAttribute("value", $("meta[name='_csrf']").attr("content"));
    articleForm.appendChild(csrfInput);

    articleForm.querySelectorAll(".file_uuid").forEach(el => {
          if(el.nextElementSibling.checked) {
            const uuidsToRemove = document.createElement("input");
            uuidsToRemove.setAttribute("type", "hidden");
            uuidsToRemove.setAttribute("name", "uuidsToDelete");
            uuidsToRemove.setAttribute("value", el.value);
            articleForm.appendChild(uuidsToRemove);
          }
        }
    );

    articleForm.action = '${contextPath}/board/modArticle.do'
    articleForm.submit();
  }

  function removeProcess(obj) {
    obj.disabled = true;
    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "${contextPath}/board/removeArticle.do");

    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name","articleNo");
    input.setAttribute("value", obj.form.articleNo.value);
    form.appendChild(input);

    const csrfInput = document.createElement("input");
    csrfInput.setAttribute("type", "hidden");
    csrfInput.setAttribute("name", $("meta[name='_csrf_parameter']").attr("content"));
    csrfInput.setAttribute("value", $("meta[name='_csrf']").attr("content"));
    form.appendChild(csrfInput);

    document.body.appendChild(form);
    form.submit();
  }

  function replyForm(obj) {
    obj.disabled = true;
    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "${contextPath}/board/replyForm.do");

    const input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name","parentNo");
    input.setAttribute("value", obj.form.articleNo.value);
    form.appendChild(input);

    const csrfInput = document.createElement("input");
    csrfInput.setAttribute("type", "hidden");
    csrfInput.setAttribute("name", $("meta[name='_csrf_parameter']").attr("content"));
    csrfInput.setAttribute("value", $("meta[name='_csrf']").attr("content"));
    form.appendChild(csrfInput);


    document.body.appendChild(form);
    form.submit();
  }
</script>

<script src="${contextPath}/resources/js/attach_file_support.js"></script>

<script>
  // show modal
  const target = document.querySelector(".modal");
  const modal = new bootstrap.Modal(target);

  if ('${msg}') {
    modal.show();
  }
</script>
