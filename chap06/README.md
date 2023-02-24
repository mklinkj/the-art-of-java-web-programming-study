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











## 의견

* 
  

## 정오표

* 없음.





## 기타

### ✨ `@WebServlet` 설정과  web.xml을 동시에 사용할 때  안 되던 문제...

이번에 다시 확인했는데...web.xml에서  `metadata-complete="true"` 설정을 제거하니 잘되었다. 

- [ ] 이유는 천천히 알아보자.😅

