# 18장 스프링 프레임워크 시작하기

> * **원래 목표가 쇼핑몰 프로젝트를 빨리 파악하고 경험해보는 것이였는데... 너무 느리게 진행하것 같다. 빨리 빨리 끝내자 😅**
> * 이제 부터는 스프링 챕터이니, 스프링 빈으로 관리할 수 있는 것은 최대한 바꿔서 사용하자..
>   * 그동안 싱글톤 인스턴스를 얻으려고 Helper 클래스들을 많이 사용했었다.. 
>   * 아직 Spring  MVC 주제는 아니니, 싱글톤 클래스들을 빈으로 바꾸는 정도만 해보자.. 의존성 주입받는식의 코드는 19장부터 같지만 필요한 부분에는 미리 적용해보자.
> * 예제 프로젝트: [pro18](pro18)



## 18.1 프레임 워크란?

* ...

### 18.1.1 스프링 프레임워크

* ...





## 18.2 스프링 프레임 워크 환경 설정하기

* 이번장에서는 싱글톤빈 활용 정도로만 사용할 것이여서, Spring Context 만 디펜던시에 추가했다. (Spring Core는 전이적 의존성으로 끌어서 가져옴.)

  ```groovy
    implementation "org.springframework:spring-context:${springVersion}"
  ```

  * spring-context 라이브러리는 스프링의 IoC 컨테이너를 나타내는 인터페이스 중 하나
  * 이 라이브러리는 BeanFactory와 ApplicationContext 인터페이스를 제공하며 이들은 스프링 IoC 컨테이너에 접근하는 루트 인터페이스임.
  * 이 라이브러리는 의존성 주입을 통해 제어의 역전을 달성함.
  * 국제화, 이벤트 전파, 리소스 로딩 및 컨텍스트의 투명한 생성과 같은 기능을 추가로 제공

  

* 그런데 MyBatis Spring은 추가해야겠다. Spring Tx는 Spring JDBC 추가되어있으니까 끌어올것 같긴한데...
  * DAO코드를 SQL 세션 노출해서 사용하는 기본 코드에서 좀 바꿔야겠음.

    ```groovy
    implementation "org.mybatis:mybatis-spring:${mybatisSpringVersion}"
    ```

* DataSource 소스 생성 및 사용도 Tomcat에 종속적이지 않게... HikariCP로 바꾸자..

  ```java
  implementation "com.zaxxer:HikariCP:${hikariCpVersion}"
  ```

* 트랜젝션의 경우, spring-jdbc를 사용하면 자동으로 끌어오긴하는데.. 이건 명시적으로 넣어주자.

  ```groovy
  implementation "org.springframework:spring-tx:${springVersion}"
  ```

  




---

## 의견

### 17장의 프로젝트를 그대로 스프링으로 전환했다.

* Spring MVC는 아직 사용하지 않았기 때문에, 서블릿은 그대로 둔 상태에서 Service, DAO, Util 클래드들을 빈으로 만들었다.

* web.xml에 추가한 Spring의 ContextListener가 application-context.xml을 참고해서 컨택스트를 만들어 서블릿 컨텍스트에 그것을 추가해 두는데, 그것을 서블릿에서 가져와서 사용하기 위해...

  * 공통 추상 서블릿의 init에 다음을 추가했다.

    ```java
      @Override
      public void init() {
        applicationContext =
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
      }
    ```

  * 하위 개별 서블릿에서는 컨택스트를 가져오기위해.. getBean()을 사용한다.

    ```java
      private  BoardService boardService;
    
      @Override
      public void init() {
        super.init();
        boardService = applicationContext.getBean("boardService", BoardService.class);
      }
    ```

    * Servlet 클래스는 빈이 아니기 때문에 다른 빈을 주입 받을 수가 없음.
    * Spring MVC로 전환해서 순수 서블릿 클래스를 겉어내면 생성자 주입을 받을 수 있다.

    

### 테스트시 Service, DAO 클래스는 `@SpringJUnitConfig ` 만 설정해서 진행하면 되는데, 서블릿 테스트의 경우  추가 설정이 필요했다.

* ContextListener가 하는일 ServletContext에 어플리케이션 컨텍스트를 바인드 하는 일을  해줄 필요가 있었는데... 이부분은 Mcok 초기화 바로 이후에 MockServletContext에 컨텍스트를 그냥 넣어줫다.

  ```java
  @SpringJUnitWebConfig(locations = "classpath:application-context.xml")
  /** Mock HTTP Servlet 테스트를 위한 공통 테스트 지원 클래스 */
  public abstract class MockHttpServletTestSupport<T extends HttpServlet> {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    ...
    protected void runGivenWhenThen(
        ExceptionableRunnable given, ExceptionableRunnable when, ExceptionableRunnable then)
        throws Exception {
      resetMock();
      servletContext.setAttribute(
          WebApplicationContext.class.getName() + ".ROOT", webApplicationContext);
      given.run();
      when.run();
      then.run();
    }
    ...
  ```

  

### MyBatis 설정을 XML 설정으로 변경

Java 설정으로 하고 있었는데... 해당 유틸리티 클래스를 제거하고 mybatis-spring을 이용하게 되어서 다음 설정을 추가했다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <settings>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
    <setting name="jdbcTypeForNull" value="NULL"/>
  </settings>
  <typeAliases>
    <typeAlias alias="article" type="org.mklinkj.taojwp.sec03.brd01.ArticleVO"/>
    <typeAlias alias="member" type="org.mklinkj.taojwp.sec01.ex01.MemberVO"/>
  </typeAliases>
</configuration>
```



* 17장에서 작성했던 모든 테스트 코드도 정상 통과 했다. 😃

  



## 정오표

* ...
  



## 기타

* ...

  