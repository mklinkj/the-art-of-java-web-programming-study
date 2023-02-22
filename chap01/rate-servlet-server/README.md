# 환율 계산기 서블릿 서버 - 예제 프로젝트

> * 환율 계산기를 웹 페이지로 제공
> * Servlet 서버



## 실행 방법

```bash
> gradle clean appRun
```

이후 브라우저에서 아래 주소로 접속

* `http://localhost:8080/calc`



## 의견

* 서블릿이라서 Gretty에 설정을 더 해줘야할 것이 있나? 했는데,
  * 프로젝트에 `src/main/webapp` 경로만 있으면 문제 없이 잘 되었다.
* Tomcat 10.1.x 환경에 jakarta.servlet-api 6.0.0으로 사용하기 때문에 서블릿 관련 패키지를 jakartra로 바꾸었다.



## 특이한점

* web.xml을 만들어 두면 `@WebServlet`가 제대로 동작하지 않았다.  

  ```java
  @WebServlet(urlPatterns = "/calc")
  public class RateServlet extends HttpServlet {
  ...
  ```

  * `@WebServlet`의 name에 설정한 경로로 접근하면 404응답을 받음.

  * Gretty + Tomcat 10.1.x 환경의 문제인지는 잘모르겠다. 일단 web.xml 참고용으로 web-example.xml로 이름만 바꿔서 보관했다.

    

## 더 해볼 일...

원래는 FreeMaker를 HTML 생성 부분에 적용해보고 싶었는데.. 이러면 JSP 파일 쓰는게 나아서... 그냥 commons-text 라이브러리로 한번 보기 좋게 변경해보았다..

* 참고
  * https://www.baeldung.com/java-string-formatting-named-placeholders

일단 HTML 보기가 편해졌음... 😄👍 수정도 쉬워졌고...





## 정오표

* [ ] p33: 코드 1-5에서 String으로 html 문자열을 response에 써주는 부분에... 여는 form 태그가 바로 닫아져있다.

  ```html
  <form  name='frmCalc' method='post' action='/rateServer/calc' />
  ==>
  <form  name='frmCalc' method='post' action='/rateServer/calc'>
  ```

  * 브라우저에서 문법 오류 보정을 해서 처리되서 그런지? 예제 동작확인에는 문제 없지만... 수정되는 것이 좋을 것 같습니다.
