function readURL(input) {
  if (input.files && input.files[0]) {
    console.log(input);

    const previewDiv = input.previousElementSibling;
    const previewImg = previewDiv.querySelector("img");
    previewImg.classList.remove('d-none');

    const reader = new FileReader();
    reader.onload = function (e) {
      previewImg.src = e.target.result;
    }
    reader.readAsDataURL(input.files[0]);
    previewDiv.classList.remove('d-none');
  }
}

function fn_add_file() {
  const newFilesDiv = document.querySelector('#new_files_div');

  // innerHTML += {preview_new_image_wrapper DIV}
  // 위처럼 전체를 통으로 넣으면 직전의 input file의 내용이 초기화되어버린다.
  // preview_new_image_wrapper 까진 HTML엘리먼트로 만들고 추가하게 하고 나서 문제가 해결됨.
  const imageWrapper = document.createElement('div');
  imageWrapper.classList.add('preview_new_image_wrapper');
  imageWrapper.innerHTML = `
       <div class="preview_new_image">
         <img src="" class="col-5 img-thumbnail rounded mx-auto d-block d-none">
       </div>
       <input type="file" name="imageFile" class="form-control mb-1"
              onchange="readURL(this)">
       <button class="btn btn-secondary" type="button" onclick="fn_reset_file(this)">취소</button>`;
  newFilesDiv.appendChild(imageWrapper);
}

function fn_reset_file(target) {
  if (target == null) {
    document.querySelector('#new_files_div').innerHTML = '';
  } else {
    target.closest('[class^="preview_new_image_wrapper"]').remove();
  }
}