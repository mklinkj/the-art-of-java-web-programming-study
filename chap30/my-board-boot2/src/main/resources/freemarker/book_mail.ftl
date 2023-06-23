<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>FreeMarker 템플릿 테스트 - 이메일</title>
</head>
<body>
<h1>신간 도서 - ${book.name}</h1>
<h3>신간 도서를 소개합니다.</h3>
<p>
    ${book.desc}
</p>
<ul>
  <li><a href="${book.link}">${book.name} 구매하러가기</a></li>
</ul>
</body>
</html>