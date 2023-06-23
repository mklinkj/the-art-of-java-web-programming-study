# 30장 스프링으로 답변형 게시판 만들기

> 이번장은 또 어떻게 해야할까?
>
> 28장에서 했던 프로젝트를 개선하는 방향으로 진행하면 좋을 것 같다.
>
> Spring 5 + Maven 프로젝트하고,  Spring Boot 3 + Gradle 프로젝트로 나눠서 하면 괜찮겠다. 
>
> * Spring Boot 2 + JSP + Maven 빌드
>   * 프로젝트: [my-board-boot2](my-board-boot2)
>   * MyBatis 사용
> * Spring Boot 3 + Thymeleaf + Gradle 빌드
>   * 프로젝트: [my-board-boot3](my-board-boot3)
>   * 먼저 장에서 JSP로 작성했던 게시판 View들을 Thymeleaf로 바꾸는게 번거롭긴 할 것 같음.
>   * JPA + Querydsl 로 바꿀수 있을까? (Oracle 계층 쿼리 목록은 Native 쿼리로 밖에 안될 것 같음.) 이 부분은
>     *  MyBatis 기반으로 우선 완료한 후, 괜찮으면 JPA로 변경 진행해보자 😅
>
> ✨ DB는 둘 다 Oracle 18c만 사용하도록 하고, `ORDER SIBLINGS BY` 를 유연하게 처리할 수 있는 방법을 찾으면 다른DB도 써보자.



* ... 



---

## 진행

* ...



## 의견

* ...



## 정오표

* ...






## 기타

### Spring Boot 2에서는 간편하게 webjar를 버전없이 사용할 줄 알았는데... 

* Spring Boot 2 + JSP 환경 에서는 잘 안된다..🎃 그래서 WebjarsLocator 컨트롤러는 유지했음.
  * 혹시 이게 JSP라서? Thymeleaf를 썻다면 잘 되었을 려나?
* Spring Boot 3 + Thymeleaf에서는 아직 해보지는 않았지만 여기선 잘 될 것 같은 느낌이 듬..



### Tiles 경고 - Tiles XML 설정 파일명에 _가 들어가면 이후의 이름 문자열을 로케일로 잘못 인식함.

```
2023-06-22 12:59:10.720  WARN 21488 --- [nio-8090-exec-3] o.a.t.r.l.PostfixedApplicationResource   : No supported matching language for locale "member". Using file:{LOCAL GIT}/the-art-of-java-web-programming-study/chap30/my-board-boot2/target/classes/tiles/tiles_member.xml as a non-localized resource path. see TILES-571
2023-06-22 12:59:10.728  WARN 21488 --- [nio-8090-exec-3] o.a.t.r.l.PostfixedApplicationResource   : No supported matching language for locale "member_ko". Using file:{LOCAL GIT}/the-art-of-java-web-programming-study/chap30/my-board-boot2/target/classes/tiles/tiles_member_ko.xml as a non-localized resource path. see TILES-571
2023-06-22 12:59:10.728  WARN 21488 --- [nio-8090-exec-3] o.a.t.r.l.PostfixedApplicationResource   : No supported matching language for locale "member_ko_KR". Using file:{LOCAL GIT}/the-art-of-java-web-programming-study/chap30/my-board-boot2/target/classes/tiles/tiles_member_ko_KR.xml as a non-localized resource path. see TILES-571
```

* tiles 설정파일 이름에는 `_`이 들어가지 않도록 해야할 것 같다. _를 빼니 경고로그가 나타나지 않음.
  * 최신 버전 (`3.0.8`) 에서도 수정된 것 같진 않음.
  * https://issues.apache.org/jira/browse/TREQ-19



### logback으로 변경 -> 그냥 지우고 application.yml에서 관리

로거는 기본으로 Spring Boot 가 제공해주는 것을 쓰는 것이 나아보여서 바꿨다. 딱히 바꿀 부분은 별로 없고 XML 설정 파일만 약간 바꿨다.

* `resources/logback-spring.xml`

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
    <!-- https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot/src/main/resources/org/springframework/boot/logging/logback/defaults.xml -->
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
      <encoder>
        <pattern>%clr(%d{HH:mm:ss.SSS}){faint} %clr([%thread]){magenta} %clr(%-5level) %clr(%logger{72}){cyan} - %clr(%msg%n){faint}</pattern>
        <!--<pattern>${CONSOLE_LOG_PATTERN}</pattern>-->
      </encoder>
    </appender>
  
    <logger name="org.mklinkj" level="debug"/>
    <logger name="org.mklinkj.taojwp.member.mapper.MemberMapper" level="trace"/>
  
    <root level="info">
      <appender-ref ref="console" />
    </root>
  </configuration>
  ```

  * 로깅 스타일 패턴은 재정의함 

파일을 그냥 두려다가... 로거를 몇개 설정한것도 없어서 `application.yml`에 정의하기로 했다. 

```yml
logging:
  pattern:
    console: "%clr(%d{HH:mm:ss.SSS}){faint} %clr([%thread]){magenta} %clr(%-5level) %clr(%logger{72}){cyan} - %clr(%msg%n){faint}"
  level:
    root: debug
    web: debug
    sql: debug
    org.mklinkj: debug
```

* 패턴을 쌍따옴표로 감싸야하는 점이 중요.

### Tomcat Context의  `allowCasualMultipartParsing=true` 설정

commons-fileupload를 활용해서 파일업로드를 하려면 allowCasualMultipartParsing 설정을 true로 설정해야해서 적용했다. (그런데 현재 시점에서 그냥 Servlet 제공 기능을 쓰면 되는데... 😅 레거시 코드 최대한 활용을 위해... 했다. 😅😅)

Tomcat의 context.xml에 설정한 아래 설정 대응

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context allowCasualMultipartParsing="true" path="/" >
  <WatchedResource>WEB-INF/web.xml</WatchedResource>
</Context>
```

설정 파일을 만들어서 해당 값이 allowCasualMultipartParsing 값이 true로 설정되도록 했다.

```java
@Configuration
public class TomcatConfig {

  @Bean
  public TomcatServletWebServerFactory tomcatFactory() {
    return new TomcatServletWebServerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        context.setAllowCasualMultipartParsing(true);
      }
    };
  }
}
```

좀 더 확인을 해봤을 때...

```java
  @Bean
  TomcatServletWebServerFactory tomcatFactory() {
    TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();

    tomcatFactory.addContextCustomizers(
        customizer -> customizer.setAllowCasualMultipartParsing(true));

    return tomcatFactory;
  }
```

위처럼 CuntextCustomizer를 람다식으로 추가해 줄수도 있었다.



### Spring Security XML과 Java 설정의 `form-login` > `login-page="/login.do"`

원래 쓰던 Spring Security Context XML 설정이 아래와 같고...

```xml
  <form-login
      login-page="/login.do"
      default-target-url="/main.do"
      authentication-failure-handler-ref="customLoginFailureHandler"
    />
```

위의 설정을 Java 설정으로 바꾸면...

```java
    http.formLogin(
        formLogin ->
            formLogin
                .loginPage("/login.do")  
                .defaultSuccessUrl("/main.do")
                .failureHandler(loginFailureHandler())
                .permitAll());
```

XML 설정의 경우는 `login-processing-url`을 `/login` 기본 설정으로 그대로 두는데, 

Java 설정의 경우는 이 값을 loginPage와 동일하게 바꿔버린다.

그래서 `/login.do`에서  로그인 폼의 POST 요청을 /login에 하게되는 상황이 발생한다.



그러면 Spring Security 필터 중에... 폼 정보로 인증을 처리하는 UsernamePasswordAuthenticationFilter 에서 인증정보를 만들지 못해서...

```
TRACE org.springframework.security.web.FilterChainProxy - Invoking UsernamePasswordAuthenticationFilter (7/13)
TRACE o.s.security.web.authentication.UsernamePasswordAuthenticationFilter - Did not match request to Ant [pattern='/login.do', POST]
...
```

그냥 익명 유저로 설정 되서 .  /login.do로 리다이렉트 되게 된다.

```java
    http.formLogin(
        formLogin ->
            formLogin
                .loginPage("/login.do")
                .loginProcessingUrl("/login") // ✨ Java 기반 설정에서는 따로 설정하지 않으면 loginPage에 설정한 경로와 동일하게 설정된다.
                .defaultSuccessUrl("/main.do")
                .failureHandler(loginFailureHandler())
                .permitAll());
```

* Java 기반 설정에서는 loginProcessingUrl 를 확실하게 명시해주면 된다.

에러가 뜨는것도 아니고 해서 문제확인이 참 힘들었는데... Spring Securty 관련 이슈라 의심되면... 시큐리티에 대해 `trace` 레벨로 로깅을 설정해서 살펴보자.

```yml
logging:
  ...
  level:
    ...
    org.springframework.security: trace
```



### Spring Boot 2에서 미리 설정된 LocaleResolver를 SessionLocaleResolver 로 바꿔서 쓰는 방법은.. 한가지 방법 밖에 모르겠다.

```java
  @Bean
  SessionLocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
    return sessionLocaleResolver;
  }
```

localeResolver 빈을 선언하면... `WebMvcConfigurationSupport` 에서 이미 선언했다고 에러남.

```java
	@Bean
	public LocaleResolver localeResolver() {
		return new AcceptHeaderLocaleResolver();
	}
```

일단은 에러 로그에서 가이드 해준대로 Spring Boot에서 정의한 빈을 오버라이드 할 수 있게 설정을 할 수 밖에 없음.

```yml
spring:
  main:
    allow-bean-definition-overriding: true
```

왠지 이렇게 하고 싶진 않은데.... 🎃 다른 방법을 모르겠음... 😂😂😂





### Spring 6 + Thymeleaf + Gradle 프로젝트는 Spring Boot 3로 쉽게 전환했다.

* 위의 시행착오를 미리 겪어서도 그렇고...

  * 앞어서 Java 설정으로 바꾼 것은 대부분 변경없이 그대로 썼음.

* Spring Boot 가 기본 뷰 템플릿이 Thymeleaf이다보니 템플릿 경로만 기본 규약대로 잘 이동해주기만 하면됨.

  * 단지 몇가지 Depreacted 경고가 나오는데..

    ```html
    <th:block th:include="layout/side :: side">
    ==>
    <th:block th:insert="~{layout/side :: side}">
    ```

    * `th:include`가 deprected 됨
    * 값  부분을 `~{ .. }`으로 감싸라고 경고나옴..  이것 정도만 고쳤음.



### WebJars 다시확인 - 해결됨.

`build.gradle`에는... 아래 디펜던시만 추가 `webjars-locator-core`의 버전은 부트가 관리함.

```groovy
implementation 'org.webjars:webjars-locator-core'
```

메이븐은 pom.xml 에 다음 추가

```xml
    <dependency>
      <groupId>org.webjars</groupId>
      <artifactId>webjars-locator-core</artifactId>
    </dependency>
```



MVC 설정에 다음 내용추가.

````java
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // ...
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/")
        .resourceChain(false);
  }
````



위와 같이 했을 때... Spring Boot 2, Spring Boot 3 모두 잘 동작했다.

WebJars Locator 컨트롤러 만든것은 필요가 없어졌으니 제거함.



