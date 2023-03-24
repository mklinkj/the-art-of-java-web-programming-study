<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>파일 업로드 Form</title>
</head>
<body>


<form name="uploadForm" method="post" enctype="multipart/form-data">
  파일1: <input type="file" name="file1"> <br>
  파일1: <input type="file" name="file2"> <br>
  매개변수1: <input type="text" name="param1"> <br>
  매개변수2: <input type="text" name="param2"> <br>
  매개변수3: <input type="text" name="param3"> <br>
  <input type="button" onclick="actionSubmit('${contextPath}/upload.do')" value="업로드 (Servlet 스팩)">
  <input type="button" onclick="actionSubmit('${contextPath}/commonsFileUpload.do')" value="업로드 (commons-upload)">
</form>
<a href="${contextPath}/index.html">처음으로 돌아가기...</a>
<script>
  function actionSubmit(action) {
    const uploadForm = document.uploadForm;
    uploadForm.action = action;
    uploadForm.submit();
  }
</script>
</body>
</html>
