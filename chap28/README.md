# 28장 스프링에서 지원하는 여러가지 기능

> * 이번장도 프로젝트를 나눠서 해보면 좋겠다.
> 
>   * 예제 프로젝트
>   * Spring 6 + Gradle 빌드
>     * [pro28](pro28)
>       * Servlet 3 부터 서블릿 컨테이너 자체에 내장된 기능으로 사용.
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

## 스프링 시큐리티와 multipart-form (Spring 5 + Spring Security5 + Servlet 4 + JSP)

### 디펜던시 추가

```xml
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>${commons-fileupload.version}</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>${commons-io.version}</version>
    </dependency>
```



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



---

## 스프링 시큐리티와 multipart-form (Spring 6 + Spring Security6 + Servlet 6 + Thymeleaf)

### 디펜던시 추가

* 서블릿 컨테이너가 자체적으로 지원하는 기능을 사용해서, 추가할 내용 없음.



### web.xml에 디스패쳐 서블릿 설정 이하에 `<multipart-config>` 설정을 추가한다.

```xml
  <servlet>
    <servlet-name>action</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup> <!-- 값이 1 이상이면 Tomcat 실행시 미리 메모리에 로드 -->
    <multipart-config>
      <!-- 임시 파일을 저장할 공간 : 기본은 시스템 임시 폴더이다.  -->
      <!--<location>${java.io.tmpdir}</location>-->
      <!-- 업로드되는 파일의 최대 크기 -->
      <max-file-size>10485760</max-file-size> <!-- 10MB -->
      <!-- 한번에 올릴 수 있는 최대 크기 -->
      <max-request-size>52428800</max-request-size> <!-- 50MB -->
      <!-- 파일이 메모리에 기록되는 임계값  -->
      <file-size-threshold>10485760</file-size-threshold> <!-- 10MB -->
    </multipart-config>
  </servlet>
```

* location 같은 경우는 시스템 기본 값을 쓰게하는 게 낫겠다.



### Thymeleaf에서는 백틱으로 감싼 JavaScript 구문에 모델 변수 값을 사용할 때.. 이스케이프를 하지 말아야한다.

```html
   $("#d_file").append(
        `<div class="mb-3">
          <label for="formFile${count}" class="form-label">${count}번 파일</label>
          <input class="form-control" type="file" id="formFile${count}" name="file${count}">
        </div>`
    );
```

✨ 예상외로 별로 바꿀 것이 없었다. 컨트롤러 코드자체가 Spring Web이하의 클래스만 쓰다보니.. 그대로 활용가능 했다.
