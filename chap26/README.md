# 26장 스프링 애너테이션 기능

> * 이미 어노테이션 컨트롤러를 써와서 특별히 수정할 내용은 없긴한데.. 
>   * 그러면 로그인 기능을 `Spring Security`를 사용해서 구현해보자..
> * 예제 프로젝트: 
>   * [pro26](pro26)



## 26.1 스프링 애너테이션이란?

* ...

### 26.1.1 스프링 애너테이션 제공 클래스

* **DefaultAnnotationHandlerMapping**

  * 스프링 3.2에서 Deprecated 됨, RequestMappingHandlerMapping를 사용하라함.
  * https://docs.spring.io/spring-framework/docs/4.3.7.RELEASE_to_4.3.8.RELEASE/Spring%20Framework%204.3.8.RELEASE/org/springframework/web/servlet/mvc/annotation/DefaultAnnotationHandlerMapping.html

* **AnnotationMethodHandlerAdapter**

  * 스프링 3.2에서 Deprecated 됨, RequestMappingHandlerAdapter를 사용하라함.

  * https://docs.spring.io/spring-framework/docs/3.2.2.RELEASE_to_4.0.0.M1/Spring%20Framework%204.0.0.M1/org/springframework/web/servlet/mvc/annotation/AnnotationMethodHandlerAdapter.html



어노테이션 컨트롤러를 사용하기위해 설정할 때.. 위의 두클래스를 따로 설정할 필요는 없었는데...

컨트롤러에 `@Controller` 어노테이션 붙이고,  서블릿 컨텍스트 설정 파일에 아래 내용만 추가했다.

```xml
<context:component-scan base-package="org.mklinkj.taojwp">
  <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<mvc:annotation-driven/>
```

* `<mvc:annotation-driven/>` 이 HandlerMapping, HandlerAdapter를 자동으로 등록해주는 것 같다.



### 26.1.2 `<context:component-scan >` 태그 기능

* ...



## 26.2 스프링 애너테이션 이용해 URL 요청 실습하기

* ...



## 26.3 스프링 애너테이션 이용해 로그인 기능 구현하기

* ...
* 이부분은 Spring Securty 사용해서 회원 테이블 내용과 연동했다.



### 26.3.1 메서드에 `@RequestParam` 적용하기

* ...
* 이제 Java 8부터는 메서드 인자 파라미터 이름을 동일하게 쓰면 반드시 명시 않아도 되는 것 같긴함.

### 26.3.2 `@RequestParam`의 required 속성 사용하기

* ...

### 26.3.3 `@RequestParam` 이용해 Map에 매개변수 값 설정하기

* ...

### 26.3.4 `@ModelAttribute` 이용해 VO에 매개변수 값 설정하기

* ...
* 결국은 자동으로 `request.setAttribute("이름", 도메인_객체)` 이걸 해준다는 것과 같은 의미 같다.



### 26.3.5 Model 클래스 이용해 값 전달하기

* ...



## 26.4 `@Autowired` 이용해 빈 주입하기

* ...

* 요즘은 생성자 주입 방식을 사용함.

* MemberVO 를 빈으로 만드신 것은 진짜 이상하다.. 😥

  



---

## 진행

* ...
  



## 의견

* 이번 장은 주로 스프링 시큐리티를 붙이는데 시간을 많이 썻다.
  * 단순하게 써서... 나중에 답글형 게시판, 쇼핑몰할 때.. 상세하게 사용해보자 👍
    * 바로 생각는게... post 작업을 시도하다가 Login이 걸리면 그 이전작업으로 잘 안돌아간다는? 천천히 생각해보자
      * 회원 목록 메인 페이지 접근 -> 삭제버튼 누름 -> 로그인 페이지 -> ?
        * 목록으로 돌아갔으면 하는데.. 수정 페이지로 간 것 같다.  천천히.. 생각해봐야지...






## 정오표

* 943쪽 

  * MemberVO를 왜? @Component를 붙여 빈으로 만드셨을까?

  * Controller의 addMember에서 MemberVO를 인자로 받을 때.. 알아서 객체를 생성해주는데...? 

    

    




## 기타

### Spring Security 설정

* 설정을 일단은 XML 방식으로 해야겠지?...
  * Java설정이 편한긴 한데...음...



### `Service <-> Mapper`  관계에서  `Service <-> Repository <-> Mapper` 로 변경

* 아직 감은 잘 안오지만...  계층 생략이 좋지 않은 패턴이라해서, Repository 단계를 추가하고 Repository에서 Mapper를 사용하도록 변경했다.





## Spring Security 설정

> 내용이 길어질 것 같으니... 따로 분리하자!

### 라이브러리 추가

```groovy
// 아래 2가지는 필수이고...
implementation "org.springframework.security:spring-security-web:${springSecurityVersion}"
implementation "org.springframework.security:spring-security-config:${springSecurityVersion}"

// View를 JSP로 사용하니 태그라이브러리도 추가해보자.
implementation "org.springframework.security:spring-security-taglibs:${springSecurityVersion}"

// 로그인 처리가된 테스트도 해야하니... 아래도 추가.
testImplementation "org.springframework.security:spring-security-test:${springSecurityVersion}"

```



### MVC Matcher를 사용하게 될 때.. 오류

> Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'mvcHandlerMappingIntrospector' available: A Bean named mvcHandlerMappingIntrospector of type org.springframework.web.servlet.handler.HandlerMappingIntrospector is required to use MvcRequestMatcher. Please ensure Spring Security & Spring MVC are configured in a shared ApplicationContext.

* mvcHandlerMappingIntrospector 란 이름으로 HandlerMappingIntrospector 빈설정을 해주면 해결이 됨.

  ```xml
  <b:bean id="mvcHandlerMappingIntrospector" class="org.springframework.web.servlet.handler.HandlerMappingIntrospector" />
  ```

* https://stackoverflow.com/questions/75081665/bean-named-mvchandlermappingintrospector-of-type-org-springframework-web-servlet



### 테스트 코드 수정

아직 DB의 회원과 연동없이 static 유저를 넣어서 테스트 하고 있는데...  

1. 테스트 컨트롤러의 Spring Security를 활성화하기 위해 아래 설정 추가

   ```java
    @BeforeEach
     public void beforeEach() {
       mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
     }
   ```

   `.apply(springSecurity())` 부분이 추가 되었다.

2. 유효한 로그인 사용자 설정

   > 아직은 설정 파일에 정적 유저로만 등록해놔서 해당 유저 ID만 적어놨다.
   >
   > ```java
   > @WithUserDetails("mklinkj")
   > class MemberControllerTests {
   >   // ...
   > ```

3. csrf 설정

   * POST, PUT, DELETE 등의 요청에 csrf 토큰이 필요한데...

   * 어떤 페이지는 알아서 _csrf 토큰을 자동으로 삽입해주는데...  

     ```html
     <input type="hidden" name="_csrf" value="X53rbPbudvHf7yVg20l67mxozog4lcPR0TfGo9Vphf1Op2m6O_6NWpXWEpXyjBAEvWRO3A9f47FcrPP85AP2k-NZ4M8tl1GP" />
     ```

     목록에 삭제버튼마다 form달은 좀 복잡한 경우는 csrf hidden 필드가 자동으로 추가되지 않는다.

     자동으로 form이 삽입 안되는 부분은 직접 넣어줘야할 듯... 넣어주니 삭제 버튼이 정상동작한다.
     
     ```html
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     ```
     
     > Spring Security에서는 CSRF 보호가 기본적으로 설정됩니다. [이는 GET 요청을 제외한 상태를 변경할 수 있는 POST, PUT, DELETE 요청으로부터 보호합니다 . 따라서 HTML에서 다음과 같은 CSRF 토큰이 포함되어야 요청을 받아들이게 됨으로써 위조 요청을 방지하게 됩니다.
     >
     > ```
     > <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     > ```
     >
     > 그러나 단순한 페이지와 다르게 한 페이지에 여러 개의 폼이 있는 경우에는 자동으로 삽입되지 않습니다. 이 경우에는 수동으로 폼에 CSRF 토큰을 추가해야 합니다.
     
     AI한테 물어보니.. 원래 폼이 여러개면 알아서 안넣어주나보네... 😅😅😅
     
   * 스프링 시큐리티 태그라이브러리를 사용한다면...
   
     ```jsp
     <sec:csrfInput/>
     ```
   
     위의 태그를 써줘도 동일한 csrf hidden 폼을 만들어낸다.
   



### JSP에서 로그인 여부 판단

```jsp
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <!-- 로그인한 사용자가 볼 수 있는 내용 -->
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
  <!-- 로그인하지 않은 사용자가 볼 수 있는 내용 -->
</sec:authorize>
```

그리고 이 태크를 사용하려면. 아래 빈설정이 필요했다.

```xml
<b:bean id="webexpressionHandler" class="org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler" />
```



### Refused to apply style from `'http://localhost:8090/pro26/login.do'` because its MIME type ('text/html') is not a supported stylesheet MIME type, and strict MIME checking is enabled.

위와 같은 브라우저 콘솔 오류가 나타나면서 스타일이 적용되지 않는 문제가 있었음.

```xml
<intercept-url pattern="/webjars/**" access="permitAll()"/>
```

* Bootstrap CSS를 정상 로드해지 못해서 생긴 문제임.
* webjar 경로 이하는 허용되게 해주었음.



### 로그인 실패시 메시지 추가

* CustomLoginFailureHandler 단순하게 구현했음.
* 얼럿 영역은 부트스트랩으로 모양좋게 표현하는 방법이 있었다.
  * https://getbootstrap.com/docs/5.3/components/alerts/





### 참고

* Hello Spring Security Xml Config
  * https://docs.spring.io/spring-security/site/docs/5.5.x/guides/helloworld-xml.html

* [A Guide to CSRF Protection in Spring Security | Baeldung](https://www.baeldung.com/spring-security-csrf)

* [Spring Security - No visible WebSecurityExpressionHandler instance could be found in the application context](https://stackoverflow.com/questions/11594104/spring-security-no-visible-websecurityexpressionhandler-instance-could-be-foun)

  
  
  