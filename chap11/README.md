# 11장 JSP 정의와 구성 요소

> * JSP부터는 많이 써왔어서, 이미 알고 있는 내용이 많이 나올 것 같다. 😀
> * 예제 프로젝트: [pro11](pro11)



## 11.1 JSP 등장 배경

* ...

### 11.1.1 서블릿으로 화면 구현 시 문제점 

* ...
* 그래도 보통은 마크업으로 html, css, 이미지 파일로 마크업 담당 부서로 부터 전달받고 JSP로 입히는 것은 웹 개발자가 같이 했음. 요즘은 어떻게 하는지 모르겠다..

### 11.1.2 JSP의 구성 요소

* ...



## 11.2 JSP의 3단계 작업 과정

* ...

### 11.2.1 톰캣 컨테이너에서 JSP 변환 과정

* 변환 과정 3단계
  1. 변환 단계 `JSP` `->` `Java File`
  2. 컴파일 단계: `Java File` `->` `Class File`
  3. 실행단계: 컨테이너에서 Class File 실행



### 11.2.2 이클립스에서 JSP 변환 과정 실습

* ...

* IntelliJ 실행중이니 IntelliJ에서 확인하자...

* IntelliJ에서도 톰켓 연동하면 어딘가로 설정 복제해서 다른 경로로 톰켓을 띄움.

  ```
  C:\Users\{사용자이름}\AppData\Local\JetBrains\IntelliJIdea{버전}\tomcat\{프로젝트UID}\work\Catalina\localhost\pro11\org\apache\jsp
  ```

  * 위의 경로 가보면...  JSP에서 Java로 변환된 파일과 클래스 파일 있음.
    * hello_jsp.java
    * hello_jsp.class



## 11.3 JSP 페이지 구성요소

* ...

  

## 11.4 디렉티브 태그

* ...

### 11.4.1 페이지 디렉티브 태그 정의와 사용법

* ...



### 11.4.2 페이지 디렉티브 태그 사용 예제

* ...
* 한번 페이지에 접근을 해야 java, calss 파일이 생성됨



### 11.4.3 인클루드 디렉티브 태그 정의와 사용법

* ...

* 이건 tiles와 함께 많이 써봣다.

  ```jsp
  <%@ include file="공통기능.jsp" %>
  ```

* include 한 내용을 보았을 때...

  ```java
        out.write("\r\n");
        out.write("<html>\r\n");
        out.write("<head>\r\n");
        out.write("  <title>인클루드 디렉티브</title>\r\n");
        out.write("</head>\r\n");
        out.write("<body>\r\n");
        out.write("  <h1>안녕하세요. 쇼핑몰 중심 JSP 시작입니다.!!!</h1>\r\n");
        out.write("  ");
        out.write("\r\n");
        out.write("<html>\r\n"); // include 영역 시작
        out.write("<head>\r\n");
        out.write("  <title>스마일 이미지</title>\r\n");
        out.write("</head>\r\n");
        out.write("<body>\r\n");
        out.write("<img src=\"image/smiling-eyes.png\">\r\n");
        out.write("</body>\r\n");
        out.write("</html>\r\n"); // include 영역 끝
        out.write("\r\n");
        out.write("  <h1>안녕하세요. 쇼핑몰 중심 JSP 끝 부분입니다.!!!</h1>\r\n");
        out.write("\r\n");
        out.write("</body>\r\n");
        out.write("</html>\r\n");
  ```

  페이지 내용이 그대로 포함됨.

  java, class 파일은 포함자 딱 한개만 생성됨.





---

## 의견

* 이번 11장은 그다지 내용이 많지 않았다. 다음 장부터 좀 많은듯...

* ...

  

## 정오표

* ...


