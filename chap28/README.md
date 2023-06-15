# 28장 스프링에서 지원하는 여러가지 기능

> * 이번장도 프로젝트를 나눠서 해보면 좋겠다.
> 
>   * 예제 프로젝트
>   * Spring 6 + Gradle 빌드
>     * [pro28](pro28)
>       * Servlet 3 부터 자체 내장된 기능으로 사용.
>   * Spring 5 + Maven 빌드
>       * [pro28-maven](pro28-maven)
>     * commons-fileupload 사용.









---

## 진행

* ...
  



## 의견

* ...



## 정오표

* ...






## 기타

### getOriginalFilename()

> 클라이언트의 파일 시스템에 있는 원본 파일명을 반환합니다.
> 사용하는 브라우저에 따라 경로 정보가 포함될 수 있지만 일반적으로 Opera 이외의 브라우저에서는 포함되지 않습니다.
> 참고: 이 파일 이름은 클라이언트가 제공한 것이므로 무턱대고 사용해서는 안 된다는 점에 유의하세요. 디렉토리 부분을 사용하지 않는 것 외에도 파일 이름에 '..' 등의 문자가 포함될 수 있으며 악의적으로 사용될 수 있습니다. 이 파일명을 직접 사용하지 않는 것이 좋습니다. 가급적 고유한 파일명을 생성하고 필요한 경우 이 파일명을 참조할 수 있도록 어딘가에 저장하세요.
>
> #### 반환
>
> 원본 파일 이름, 또는 다중 파트 형식에서 파일이 선택되지 않은 경우 빈 문자열, 정의되지 않았거나 사용할 수 없는 경우 null을 반환합니다.

### getFileNames()

> 이 요청에 포함된 다중 파트 파일의 매개변수 이름이 포함된 문자열 객체 이터레이터를 반환합니다. 이는 일반 매개변수와 마찬가지로 양식의 필드 이름이며, 원본 파일 이름이 아닙니다.
>
> #### 반환
>
> 파일 이름

---

## 스프링 시큐리티와 multipart-form (Spring 5 + Spring Security5 + Servlet 4)

## src/main/webapp/META-INF/context.xml 에 다음을 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context allowCasualMultipartParsing="true" path="/" >
  <WatchedResource>WEB-INF/web.xml</WatchedResource>
  <!-- <Resources cachingAllowed="true" cacheMaxSize="100000" /> --> <!-- 기본값이여서 명시할 필요없음-->
</Context>
```

Tomcat 에다 직접 설정해도 되는데, 프로젝트 별로 설정하기 위해서, 위의 위치에 설정했다.

> - **allowCasualMultipartParsing**: 이 설정은 multipart/form-data 형식의 요청을 처리하는 데 사용됩니다. 이 설정을 true로 설정하면 Tomcat은 multipart/form-data 형식의 요청을 처리할 때 Servlet 3.0 API를 사용합니다. 이 설정을 false로 설정하면 Tomcat은 multipart/form-data 형식의 요청을 처리할 때 Servlet 2.5 API를 사용합니다. Servlet 3.0 API는 Servlet 2.5 API보다 더 강력하고 기능이 풍부합니다. 따라서 이 설정을 true로 설정하는 것이 좋습니다.
> - **cachingAllowed, cacheMaxSize**: 이 설정은 리소스를 캐시하는 데 사용됩니다. 이 설정을 true로 설정하면 Tomcat은 리소스를 캐시합니다. 이 설정을 false로 설정하면 Tomcat은 리소스를 캐시하지 않습니다. cacheMaxSize는 캐시할 수 있는 리소스의 최대 크기를 지정합니다. 리소스를 캐시하면 리소스를 요청할 때마다 서버에서 리소스를 가져올 필요가 없기 때문에 성능을 향상시킬 수 있습니다. 따라서 이 설정을 true로 설정하는 것이 좋습니다. 그러나 cacheMaxSize를 너무 높게 설정하면 캐시가 너무 커져 성능이 저하될 수 있습니다. 따라서 cacheMaxSize를 적절한 값으로 설정하는 것이 중요합니다.



### web.xml의 스프링 시큐리티 필터 설정 앞에 MultipartFilter 배치

```xml
  <filter>
    <display-name>springMultipartFilter</display-name>
    <filter-name>springMultipartFilter</filter-name>
    <filter-class>org.springframework.web.multipart.support.MultipartFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>springMultipartFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- DelegatingFilterProxy ... -->
  <!-- ... -->
```



### 서블릿 컨텍스트 설정의 CommonsMultipartResolver 의 빈이름을 filterMultipartResolver로 설정

```xml
  <!-- DispatcherServlet 로딩 이전에 이미 있어야지만 MultipartFilter에서 로딩이 가능하므로 루트 컨텍스트에 선언 -->
  <bean name="filterMultipartResolver"
    class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="maxUploadSize" value="52428800"/> <!-- 50MB -->
    <property name="maxInMemorySize" value="10485760"/> <!-- 10MB -->
    <property name="maxUploadSizePerFile" value="10485760"/> <!-- 10MB -->
    <property name="defaultEncoding" value="utf-8"/>
  </bean>
```

* MultipartFilter가 기본으로 인식하는 이름이 `filterMultipartResolver` 인데, 맞춰주자.

* 이 빈설정은... DispatcherServlet 로딩 이전에 되야하므로 Root 컨텍스트에 넣어줘야 제대로 동작한다.

  * 이 Bean을 못읽을 경우..  `StandardServletMultipartResolver`로 처리되기 때문에 commons-upload 도 사용하면서 Servlet의 Multipart 기능도 사용하게되서 설정이 꼬이게 된다.

    > maxUploadSizePerFile 설정이 안먹어서, web.xml에 max-file-size 설정을 해줘야하는 설정이 중복된상태가 됨.. 😅

