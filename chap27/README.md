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





