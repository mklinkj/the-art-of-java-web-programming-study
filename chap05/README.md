# 5장 웹 애플리케이션 이해하기

> * 이번장은 Servlet 클래스 개발이 목적이니, Gradle + Gretty 프로젝트로 만들어서 진행하면 되겠따.
> * 이번엔 Servlet 클래스에 대해 테스트를 추가해보자!
>   * MockHttpServletRequet/Respose를 추가하려면 Spring Test를 추가해야할 것 같은데...
> * 예제 프로젝트: [pro05](pro05)



## 목차

### 5.1. 서블릿이란?

* https://youtu.be/9FKRpqQLMck



### 5.2 서블릿 API 계층 구조와 기능

* https://www.youtube.com/watch?v=c8A2SMzX3dI
* 음... HttpServlet의 상속관계상위에 ServletConfig 인터페이스가 있는줄은 몰랐다. 😅



### 5.3 서블릿의 생명주기 메서드

* 



### 5.4 FirstServlet을 이용한 실습

* 

#### 5.4.1 사용자 정의 서블릿 만들기

* ...

#### 5.4.2 톰캣의 servlet-api.jar 클래스 패스 설정하기

* ...

* 나는 Gretty를 사용하므로.. build.gradle을 아래와 같이 구성했다.

  ```groovy
  dependencies {
    ...
    compileOnly "jakarta.servlet:jakarta.servlet-api:${jakartaServletApiVersion}"
    implementation "org.glassfish.web:jakarta.servlet.jsp.jstl:${jakartaServletJspJstlVersion}"
    ...
    testRuntimeOnly "jakarta.servlet:jakarta.servlet-api:${jakartaServletApiVersion}"
    ...
  }
  ```

  * 프로젝트 개발 / 테스트시에는 jakarta의 라이브러리를 사용하고 실행이나 배포시에는 Tomcat 에 포함된 servlet-api.jar를 사용함.



#### 5.4.3 첫 번째 서블릿 만들기

* ...



#### 5.4.4 서블릿 매핑하기

* ...

* web.xml에 서블릿 매핑 설정

  ```xml
    <servlet>
      <servlet-name>aaa</servlet-name>
      <servlet-class>org.mklinkj.taojwp.sec01.ex01.FirstServlet</servlet-class>
    </servlet>
    <servlet-mapping>
      <servlet-name>aaa</servlet-name>
      <url-pattern>/first</url-pattern>
    </servlet-mapping>
  ```

  

#### 5.4.5 톰켓에 프로젝트 실행

* ...



#### 5.4.6 브라우저에서 서블릿 요청하기

* `http://localhost:8090/pro05/first`

* 로그 확인

  ```
  00:08:11.593 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.FirstServlet - init 메서드 호출
  00:08:11.601 [http-nio-8090-exec-1] INFO  org.mklinkj.taojwp.sec01.ex01.FirstServlet - doGet 메서드 호출
  ```

  

#### 5.4.7 다수의 서블릿 매핑하기

* ...





## 의견

* 처음에는 서블릿 테스트 할 때, HttpUnit으로 테스트를 해볼까 했음..

  * https://httpunit.sourceforge.net/doc/servletunit-intro.html
  * 지금도 잘 동작 할 것으로 보이기는하는데... 라이브러리가 2018년 이후로 업데이트되지 않고 있고, JUnit 5 환경에서 잘 동작하지 않을 수 도 있을 것 같아서... 프로젝트에 적용하진 않았다.
  
  

## 정오표

* 없음.



