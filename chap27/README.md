# 27장 메이븐과 스프링 STS 사용법

> * 이번장은 어떻게 진행해야할까? 🎃
>   * Maven 빌드 프로젝트 생성
>   
>     * 26장 Gralde 빌드로 작성한 프로젝트를 Maven 빌드 기반으로 전환해봐도 좋을 것 같긴하다.. 
>     * Gretty 대신 [cargo-maven-plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html)으로 Tomcat 실행되게 설정하자.
>   
>   * STS 사용법, MyBatis 사용 등등...
>   
>     * IntelliJ 사용중이니 넘어가자. 요즘 InteliJ의 MyBatis 지원이 꽤 좋아졌다. (Mapper 인터페이스 빈 찾을 수 없다는 경고가 있어왔는데.. 해결된듯.)
>   
>   * Apache Log4J
>   
>     * 로깅은 스터디 시작할 때부터 적용해서, 추가로 할 일은 없을 것 같다.  `Slf4j 구현으로 Log4j2 사용하고 있음.`
>   
>   * Apache Tiles
>   
>     * [Tiles](https://tiles.apache.org/framework/index.html)가 2017년 3.0.8 버전이후로 신버전이 나오지않고 개발 중단된 것 같은데...
>       * 새로 Maven 빌드로 분기한 프로젝트에는 적용해봐도 될 것 같긴함.
>     * ✨ Gradle 빌드 프로젝트의 경우... 아직 View가 복잡하진 않아서 이번기회에 `Thymeleaf`로 바꿔봐도 좋을 것 같다.
>   
>     
>   
>     	
>   
> * 예제 프로젝트
>   * Gradle 빌드
>     * [pro27](pro27)
>   * Maven 빌드
>     * [pro27-maven](pro27-maven)



## 27.1 메이븐 설치하기

* ...
* Maven에 대해서는 이미 잘 알고 있고, 잘 쓰고도 있어서...😅 
* Gradle 프로젝트도 Maven 프로젝트로 성공적으로 전환했다. 👍



## 27.2 메이븐 환경 변수 설정하기

* ...



## 27.3 STS 설치하기

* ...
* STS는 안쓰기로 함.. IntelliJ로 모든것이 아주 잘되고 있음..😅



## 27.4 메이븐 프로젝트의 구조와 구성 요소

* ...
* 솔직히 일찌감치 빌드 툴을 쓰는 식으로 진도가 구성되었어야 했다고 생각이 드는데...  
* 무의미한 노가다에 익숙해지면 안됨.. 좋은 방법이 있다면 빠르게 적용해야함.
* 지면의 pom.xml을 보니 여기는 Spring 4로 되어있음... 이전까지 라이브러리 복사는 Spring 3.0 이였던 것 같던데...



## 27.5 스프링 프로젝트 만들기

* ...



## 27.6 STS 프로젝트 실행하기

* ...
* `favicon.ico`을 넣긴했는데.. 제대로 쓸려면 Context Path를 /로 해야 브라우저에서 정상 인식한다.



## 27.7 STS 환경에서 마이바티스 사용하기

* ...

* Oracle 드라이버가 아마도... 2014년에도 Maven 레파지토리로 받을 수 있었던 것 같은데..

  * https://mvnrepository.com/artifact/oracle/ojdbc6/11.2.0.3

  * 그런데...  이부분 같은 경우는 호환성 문제가 있을 수 있어 정확히 버전 맞출려고 그랬을 수도 있겠다.

  * 나는 그냥 ojdbc 8로 쓰고 있음. (OracleXE 18c)

    ```groovy
    runtimeOnly "com.oracle.database.jdbc:ojdbc8:${ojdbcVersion}"
    ```

    

## 27.8 log4j란?

* ...
* 로거도 System.out 출력보다는 진도 초반에 설정해두고 쓰는게 좋긴했다...
* Log4j의 이전 버전들은 취약점이 많아서 항상 최신 버전을 유지하는 것이 좋음.
  * 근래에 심각한 원격 코드 실행 취약점 관련해서 재현을 해봤음.
    * https://github.com/mklinkj/log4j2-test



### 27.8.2 MyBatis SQL문을 로그로 출력하기

* ...

* ROOT로그를 debug로 설정하라고 설명을 하셨는데...

* 공식 문서에 따르면... Mapper 인터페이스에 대해 TRACE레벨로 로깅을 지정해주면 됨.. 나도 그렇게하고 있고..

  * https://github.com/mklinkj/log4j2-test

    ```xml
    <Logger name="org.mklinkj.taojwp.member.mapper.MemberMapper" level="TRACE"/>
    ```

    





























---

## 진행

* ...
  



## 의견

* ...




## 정오표

* ...






## 기타

### webjars-locator를 사용해도 이상하게 버전명시해야 접근되는 문제가 있어 검색..

* 가장 도움 되었다.
  * https://stackoverflow.com/questions/35536836/remove-webjars-version-from-url
  * 어떻게 하더라도 제대로 동작하지 않아서 직접 컨트롤러 메서드를 

* https://www.webjars.org/documentation#springmvc
* https://github.com/spring-projects/spring-boot/blob/2.0.x/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration.java
* https://www.springcloud.io/post/2021-12/client-side-development-with-spring-boot-applications/#gsc.tab=0



### 좀  더 보니.. Java 17 환경에서 WebJarAssetLocator의 getWebJars() 실행시 버전 정보를 못읽는다.. (또 나중엔 읽음 😆🎃👺)

```java
    WebJarAssetLocator LOCATOR = new WebJarAssetLocator();
    Map<String, String> webjars = LOCATOR.getWebJars();
```

```
{jquery=3.6.4, bootstrap=5.3.0}
```

위와 같은 식으로 버전이 조회가 되야하는데... 실행 코드를 그대로 두면서 Java 17로 환경을 올리면 

```
{jquery=null, bootstrap=null}
```

null로 나오는 경우가 있었는데... 지금 17다시 올리고 보니 다시 잘됨.? 😆 미치겠음...

그래도 알려진 설정으로는 절대로 버전없이 사용할 수가 없어서.. 경로를 만들어주는 컨트롤러 작성했음..

```java
@Slf4j
@Controller
public class WebJarsController {
  private static final WebJarAssetLocator LOCATOR = new WebJarAssetLocator();

  @ResponseBody
  @GetMapping("/webjars_locator/{webjar}/**")
  public ResponseEntity<Resource> locateWebjarAsset(
      @PathVariable String webjar, HttpServletRequest request) {

    try {
      String version = LOCATOR.getWebJars().get(webjar);
      String mvcPrefix = String.format("/webjars_locator/%s/", webjar);
      String mvcPath =
          (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
      String fullPath =
          String.format(
              "META-INF/resources/webjars/%s/%s/%s",
              webjar, version, mvcPath.substring(mvcPrefix.length()));

      return new ResponseEntity<>(new ClassPathResource(fullPath), HttpStatus.OK);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
```

내부 동작 확인 때문에 이렇게하긴 했는데...

* https://stackoverflow.com/questions/35536836/remove-webjars-version-from-url  이 분처럼 getFullPath() 메서드를 활용하는 것이 좋을 것 같다.
* 그냥 스프링 부트를 썼으면 알아서 설정이 되어있을 텐데...



### 동작에는 문제가 없지만... 약간 찝찝한 문제가.... 

응답의 Content-Type 값이 정확하게 나오지 않는다..

* 응답 헤더의 Content-Type이 css / js 모두  `text/html;charset=UTF-8`  로 나타남 (Firefox)

  * 원래대로라면...

    * JavaScript:  `application/javascript;charset=UTF-8`
    * css: `text/css;charset=UTF-8`

  * WebJarsController 의 메서드에다가 확장자를 보고 response에 ContentType을 설정해주면.. 되기는 될테지만.. 그렇게 까지 해야하는지? 😂😂😂

    ```
     execute script from 'http://localhost:8090/pro27/webjars_locator/jquery/jquery.slim.min.js' because its MIME type ('application/json') is not executable, and strict MIME type checking is enabled.
    listMembers.do:1 Refused to execute script from 'http://localhost:8090/pro27/webjars_locator/bootstrap/js/bootstrap.bundle.min.js' because its MIME type ('application/json') is not executable, and strict MIME type checking is 
    ```

  * 실행은 되긴 되는데.. 콘솔 에러가 나서... ㅠㅠ

    ```java
          return ResponseEntity.status(HttpStatus.OK) //
              .header(HttpHeaders.CONTENT_TYPE, getContentType(fullPath))
              .cacheControl(CacheControl.maxAge(Duration.ofDays(7)))
              .body(new ClassPathResource(fullPath));
    ```

    반환할 때... 확장자 보고 컨텐트 타입을 지정하게 했다..

    * 특이하게도 캐시를 설정안하면 항상 새로받아서 설정해야했다. (css파일 자체가 커서...ㅠㅠ)



그런데 아무리 그래도 웰케 느리지!! 확인해보니... 크롬 네트워크 탭에 `느린 3G`로 설정되어있었음 😂😂😂

![image-20230607212310921](doc-resources/image-20230607212310921.png)

그래도 캐시 문제 확인하는데는 도움이 되었음 👍





### 컨텐츠 타입을 확장자 보고 수동으로 판단했었는데...

Java 1.7부터는 `Files.probeContentType()`란 메서드가 준비되어있다.

```java
Files.probeContentType(Path.of(classPathResource.getPath())))
```

css나 javascript 리소스를 정확하게 판단할 수 있었다.
