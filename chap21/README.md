# 21장 스프링 MVC 기능

> * 회원제 게시판을 바로 수정하지말고 단순 예제만 진행하자.
> * 책의 기준이 스프링 3.0 기준이라서 현시점에서 (5, 6) Dprecated 된 사용법들이 좀 있긴하다..
>   * 버전을 5 보다 이전으로 낮춰서 테스트 하는 것은 별로 의미가 있는 것인지는 잘 모르겠지만... 일단 진행해보자.
> * 예제 프로젝트: 
>   * Spring 4 프로젝트
>     * [pro21-spring4](pro21-spring4)



## 21.1 스프링 프레임워크 MVC의 특징

* ...



## 21.2 SimpleUrlController 이용해 스프링 MVC 실습하기

* ...

  

## 21.3 MultiActionController 이용해 스프링 MVC 실습하기

* ...








---

## 진행

* ...



## 의견

* ...



## 정오표

* ...
  



## 기타

### commong-logging 제외 설정

Spring 5, 6 사용할 때는 별로 신경쓰이지 않았던 부분인데...  Spring 4로 버전을 내리고 나니까 문제가 나타남.

구현체를 log4j2로 사용하면서 SLF4J를 사용하고 있었는데, 이전 버전은 commons-logging을 기본으로 사용하기 때문에 비활성화할 필요가 있음.

* build.gradle

  ```groovy
  dependencies {
    // log4j를 사용하므로 commons-logging은 전역적으로 제거.
    // Spring 5, 6 에서는 디펜던시가 걸리지 않았던 것 같은데, 4를 사용하다보니 commons-logging이 디펜던시 되어 제거했다.
    configurations.all {
      exclude group: "commons-logging", module: "commons-logging"
      exclude group: "org.slf4j", module: "slf4j-jcl"
    }
  
    // Spring에서 내부적으로는 commons-logging의 인터페이스로 로깅을 사용하므로, 다른 방식으로 마이그레이션해서 사용할 수 있도록 라이브러리 추가가 필요하다.
    // * https://www.slf4j.org/legacy.html#GradualMigrationTo%20%20%20SLF4JFromJakartaCommonsLogging%20(JCL)
    runtimeOnly "org.slf4j:jcl-over-slf4j:${slf4jVersion}"
    implementation "org.apache.logging.log4j:log4j-slf4j2-impl:${log4jVersion}"
    ...
  ```

  위처럼 전역에서 commons-logging의 디펜던시를 제거하고.  jcl-over-slf4j 를 추가해서 스프링의 commons-logging 코드가 호환되게 했음.

* https://spring.io/blog/2009/12/04/logging-dependencies-in-spring





### Spring 4가 JUnit 5를 정식 지원하지 않음.

* 외부 라이브러리를 사용하면 할 수는 있을 것 같긴한데... 임시방편적인 방법이라 JUnit 4를 사용하기로 했다.

  * https://antkorwin.com/junit5/junit5_in_spring4.html

* Gradle 최신버전(`8.x`)과 함께 잘 사용하기 위해서는... 

  ```groovy
    // Spring 4 환경에서는 JUnit 5를 정식 지원하지는 않는다.
    testImplementation "org.junit.vintage:junit-vintage-engine:${junitVersion}"
  ```

  `junit-vintage-engine`을 사용해주면 된다. (내부에서 JUnit 4 최신 버전을 디펜던시함.)

> Spring 5나 6이라면 그냥 `@SpringJUnitWebConfig`만 붙여주고 MockMvc도 자동으로 넣어줬던 것 같은데...
>
> Spring 4 환경에서는 다음과 같이해줘야했음.
>
> ```java
> @RunWith(SpringRunner.class)
> @ContextConfiguration("file:src/main/webapp/WEB-INF/action-servlet.xml")
> @WebAppConfiguration
> public class SimpleUrlControllerTest {
>   @Autowired private WebApplicationContext context;
> 
>   private MockMvc mockMvc;
> 
>   @Before
>   public void before() {
>     mockMvc = webAppContextSetup(context).build();
>   }
>   ...
> ```



