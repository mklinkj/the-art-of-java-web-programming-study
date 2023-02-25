# 6장 서블릿 기초

> * 서블릿에 대해 잘 아는 것도 아니여서... 지금까지 본 내용도 꽤 도움이 되는 것 같다. 가볍게 볼것이 아님.. 😄
> * 예제 프로젝트: [pro06](pro06)



## 목차

### 6.1 서블릿의 세 가지 기본 기능

* ...

* https://youtu.be/QP_RIvvJPYA

  

#### 6.1.1 서블릿 기본 기능 수행 과정

* ...



#### 6.1.2 서블릿 응답과 요청 수행 API 기능

* ...



### 6.2 `<form>` 태그 이용해 서블릿에 요청하기

#### 6.2.1 태그로 서블릿에 요청하는 과정

* ...

  

#### 6.2.2 `<form>` 태그의 여러가지 속성

* ...



### 6.3 서블릿에서 클라이언트 요청을 얻는 방법

* ...

  

#### 6.3.1 HttpServletRequest로 요청 처리 실습

* ...

* 동작 테스트

  ```
  3:06:21.106 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.LoginServlet - init 메서드 호출
  03:06:21.116 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.LoginServlet - 아이디: 1
  03:06:21.116 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.LoginServlet - 암호: 2
  03:06:32.361 [http-nio-8090-exec-2] INFO  org.mklinkj.taojwp.sec01.ex01.LoginServlet - 아이디: choi
  03:06:32.361 [http-nio-8090-exec-2] INFO  org.mklinkj.taojwp.sec01.ex01.LoginServlet - 암호: 1212
  ```

  

#### 6.3.2 여러 개의 값을 전송할 때의 요청 처리

* ...

* 동작 확인

  ```
  03:38:57.948 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - init 메서드 호출
  03:38:57.956 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - 아이디: choi
  03:38:57.957 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - 암호: 1212
  03:38:57.957 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - 선택한 과목: java
  03:38:57.957 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - 선택한 과목: JSP
  03:38:57.957 [http-nio-8090-exec-3] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet - 선택한 과목: 안드로이드
  ```

  

#### 6.3.3 getParameterNames() 메서드를 이용한 요청 처리

* ...

* 동작 확인

  ```
  04:09:48.990 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - init 메서드 호출
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=user_id, value=choo
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=user_pw, value=1234
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=subject, value=java
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=subject, value=C언어
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=subject, value=JSP
  04:09:49.000 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.InputServlet2 - name=subject, value=안드로이드
  ```

  index.html의 action 주소값을 바꾸지 않고, 라디오 버튼으로 서블릿 주소를 선택할 수 있게 구현했다.



### 6.4 서블릿의 응답 처리 방법

* ...

#### 6.4.1 MIME-TYPE

* Tomcat 컨테이너에서 미리 설정해 놓은 데이터 종류

* `${CATALINA_HOME}/conf/web.xml` 의 Default MIME Type Mappings 이하 항목에 미리 정의된 매핑 정보들이 있음.

  ```xml
  <!-- html확장자의 예.. -->
  ...
      <mime-mapping>
          <extension>html</extension>
          <mime-type>text/html</mime-type>
      </mime-mapping>
  ...
  ```




### 6.4.2 HttpServletResponse를 이용한 서블릿 응답 실습

* ...



#### 6.4.3 서블릿을 이용한 환율 계산기 예제 실습

* ...





### 6.5 웹 브라우저에서 서블릿으로  데이터 전송하기



#### 6.5.1 GET/POST 전송 방식

* ...



#### 6.5.2 GET 방식으로 서블릿에 요청

* ... 



#### 6.5.3 POST 방식으로 서블릿에 요청

* ...



### 6.6 GET 방식과 POST 방식 요청 동시에 처리하기

* ...



### 6.7 자바스크립트로 서블릿에 요청하기

* 문서에서 form을 찾는법

  ```javascript
  const formElement = document.폼의_name속성;
  ```

* 코드 6-14를 보면 입력값 검증코드가 추가되었는데... 이부분을 javascript 라이브러리를 이번기회에 사용해볼까? 하다가 그냥 책 내용대로 하는 것이 낫겠다.

* 테스트 URL

  * `http://localhost:8090/pro06/login2.html` 

  



### 6.8 서블릿을 이용한 여러가지 실습 예제

* ...

#### 6.8.1 실습 예제1: 서블릿에 로그인 요청 시 유효성 검사하기

* ...

#### 6.8.2 실습 예제2: 서블릿으로 로그인 요청시 관리자 화면 나타내기

* ...

#### 6.8.3 실습 예제3: 서블릿으로 요청 시 구구단 출력하기

* ...
* React로만 먼저 해볼까? 😅
  * `http://localhost:8090/pro06/test01/guguReact.html`
    * [pro06/src/main/webapp/test01/guguReact.html](pro06/src/main/webapp/test01/guguReact.html)
  * 조금 시간은 걸렸지만 기능은 동일하게 구현했다..😄
    * 색상 스타일은 그냥 안함..😅
* 서블릿으로 하니까? 뭔가 수정할 때마다 Tomcat 재시작 하는게... 힘들다. 😂
  * 색깔 교대로 바꾸는 것은 따로 클래스 새로 만들어서 할 필요는 없을 것 같아서. GuguTest에다 했다.



## 의견

* 이제부터는 동영상은 막히는 거 있을 때 보면 되겠다, 바로바로 링크 걸지말고.. 천천히 걸어도 될 것 같음.
* 6장 내용도 꽤 많았었는데.. 잘 마쳤다.

## 정오표

* 없음.





## 기타

### ✨✨✨ [HttpServletResponse.getOutputStream()/.getWriter()에서 .close()를 호출해야 합니까?](https://stackoverflow.com/questions/1159168/should-one-call-close-on-httpservletresponse-getoutputstream-getwriter)

* 책에서도 일부러 PrintWriter를 닫지 않았지만.. 난 습관상 일부러 닫긴 했는데... 이걸 닫는 것은 컨테이너의 책임이 맞는 것 같다.

* ✨ **직접 열지 않았다면 닫지 마라..가 맞는 말 같다.** 

  ```java
  // ServletResponseWrapper 클래스의 getWriter()
  @Override
  public PrintWriter getWriter() throws IOException {
    return this.response.getWriter();
  }
  ```

  

   

### ✨ `@WebServlet` 설정과  web.xml을 동시에 사용할 때  안 되던 문제...

이번에 다시 확인했는데...web.xml에서  `metadata-complete="true"` 설정을 제거하니 잘되었다. 

- [ ] 이유는 천천히 알아보자.😅



### commons-text의 `StrSubstitutor`은 Depreated 되었다. `StringSubstitutor`을 사용해야한다.

* 클래스 명에 축약된 이름을 안쓰기로 해서 그런 것 같은데...
* StrSubstitutor가 동일한 기능으로 log4j에도 포함되어있는데.. 이건 사용하지 않도록 하자.. 😅
