# 10장 서블릿 필터와 리스너 기능

> * 기초는 중요하지 않은 부분이 없음... ✨
> * 이번엔 IntelliJ에서 Tomcat 10.1.x를 연동해서 사용하자! 다른 스터디에서 설정했을 때 엄청 편했음.👍
> * 예제 프로젝트: [pro10](pro10)



## 목차

### 10.1 서블릿 속성과 스코프

* 서블릿 속성: 다음 3가지 서블릿 API클레스에 저장되는 객체

  1. ServletContext
  2. HttpSession
  3. HttpServletRequest

  

* 서블릿 스코프
  * 서블릿 API에 바인딩된 속성에 대한 접근 범위

* 스코프 종류
  | 스코프 종류         | 해당 서블릿 API    | 속성의 스코프     |
  | ------------------- | ------------------ | ----------------- |
  | 애플리캐이션 스코프 | ServletContext     | 애플리케이션 전체 |
  | 세션 스코프         | HttpSerssion       | 브라우저          |
  | 리퀘스트 스코프     | HttpServletRequest | 해당 요청         |

* ...



### 10.2 서블릿의 여러가지 URL 패턴

* ...
* URL 패턴: 반드시 /로 시작해야함.



#### 10.2.1 서블릿에 여러가지 URL 패턴 적용 실습

* ...
  * 완전일치가 최우선
  * 그 다음 경로
  * 그 다음 와일드 카드 확장자 매핑



### 10.3 Filter API

* ...
* 서블릿에서 반복적으로 처리해야하는 작업을 필터 클래스로 설정해두면 좋음 👍



#### 10.3.1 사용자 정의 Filter 만들기

* ...



#### 10.3.2 Filter를 이용한 한글 인코딩 실습

* ...

* Tomcat 10.1.7의 경우 request, response 인코딩에 대해 기본으로 UTF-8 설정이 들어가 있다..

  * tomcat conf의 web.xml

    ```xml
      <!-- Set the default request and response character encodings to UTF-8.   -->
      <request-character-encoding>UTF-8</request-character-encoding>
      <response-character-encoding>UTF-8</response-character-encoding>
    ```

    한글이 깨지는 것을 확인하려면 저 내용을 주석 처리해야함.

    * 책의 환경과 맞춰야하니 주석으로 바꿔두었다.



#### 10.3.3 응답 필터 사용

* ...
* chain.doFilter() 메서드 기준으로 위쪽에 위치한 코드는 요청 필터 기능 수행, 그 아래는 응답 필터 기능 수행.



#### 10.3.4 응답 필터 기능으로 작업 시간 구하기

* ...
* 필터는 테스트를 어떻게 해야할까? 천천히 생각해보자 😑



### 10.4 여러가지 서블릿 관련 Listener API

* ...

  

#### 10.4.1 HttpSessionBindingListener를 이용해 로그인 접속자수 표시

* ...



#### 10.4.2 HttpSessionListener 이용해 로그인 접속자수 표시

* ...

* 앞에 index페이지로 jsp파일을 두었는데.. 이러면 인덱스 페이지 접근만으로도 일단 세션이 생긴 것으로 간주된다.

  * 그래서 p381에서 세션이 이미 있다고 간주되서 최초 접속시 ID가 안들어감. 우선 index.jsp를 다시 html로 바꿈.

* LogoutTest 클래스에서 특이한 부분..

  ```java
      List<String> userList = (List<String>) context.getAttribute("userList");
      userList.remove(userId);
      // userList가 컨텍스트에 존재하는 동일한 참조인데.. 참조만 지웠다가 재할당하는 부분이 필요한 부분인지?
      // 새로 복사해서 만들었다면 재할당하는게 맞긴한데...
      // context.removeAttribute("userList");
      // context.setAttribute("userList", userList);
  ```

  읻단 컨텍스트에대해 특별한 이벤트리스너가 걸려있지 않은 이상은 불필요한 부분 같은데... ❓❓❓

* 그리고 context.xml에는 아래 설정 넣기로 했다. `""` 로 설멍하면 세션을 명시적으로 직렬화하지 않겠다는 의미가 확실해져서...

  ```xml
    <!-- "" 으로 직접 명시하면 세션의 직렬화를 하지 않겠다고, 정확하게 정의하는 것이여서 저자님 말씀대로 빈 문자열을 설정해두는 것이 낫겠다.-->
    <Manager pathname="" />
  ```

  

## 의견

* 이번 장도 중요한 내용이였다... 빨리 Spring 직전까진 진도가 나가야되는데...😓

  

## 정오표

* ...



### 최신환경과 호환되지 않는 내용

* p372: 코드 10-8

  * request.getRealPath(pathinfo) `->` this.context.getRealPath(pathinfo)

  * getRealPath()메서드가 ServletContext에 있다.

    * https://jakarta.ee/specifications/platform/10/apidocs/jakarta/servlet/servletcontext#getRealPath-java.lang.String-

  * JavaEE 6 API(Servlet Spec 3.0)를 보니 예전에 있었는데.. 이미 Deprecated 된 상태였고, Jakarta EE 10(Servlet Spec 6) 에서는 메서드가 삭제됨

    ```
    Deprecated. As of Version 2.1 of the Java Servlet API, use ServletContext#getRealPath instead.
    ```

    * https://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html#getRealPath(java.lang.String)



## 기타

* p360~361 예제 보니 request로 부터 얻은 PrintWriter를 close하는 코드가 들어가던데.. 이걸 닫는 건 컨테이너의 책임이라고 생각해서.. 나는 닫지 말자..

* String으로 HTML을 치는 일이 많은데 기본 형태를 IntellJ의 Live Template로 지정해두자

  ```java
  """
  <!DOCTYPE html>
  <html lang="ko">
  <head>
    <meta charset="UTF-8">
    <title></title>
    <style>   
    </style>
  </head>
  <body>
    
  </body>
  </html>
  """
  ```

  ![image-20230306031402963](doc-resources/image-20230306031402963.png)

  * jhtml 치면 바로 템플릿 문자열 추가됨, 변수화 시키는 부분도 있는 것 같은데.. 그부분은 나중에..😄



### Mock 생성 변경

서블릿 클래스의 Mock을 다음과 같이 생성했었는데..

```java
T mockServlet = Mockito.mock(servletClass, Mockito.CALLS_REAL_METHODS);
```

이렇게 할경우 서블릿에 다음과 같이 필드 초기화 코드가 있을 경우 초기화가 안되고 null인 상태로 있게된다.

```java
private final List<String> userList = new CopyOnWriteArrayList<>();
```

그래서 Mock 초기화를 아래처럼 바꿈.

```java
T mockServlet = Mockito.spy(servletClass);
```



