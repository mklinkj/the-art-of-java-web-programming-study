# 7장 서블릿 비즈니스 로직 처리 : 예제 프로젝트 - pro07

> * ...
>
> * Gretty에서 ojdbc 드라이버를 잘 쓸 수 있을까? 초반의 JNDI 사용하지 않는 부분까지는 별문제 없을 것 같다.
>
>   나의 환경에서는 ojdbc8을 사용해보자.
>
>   * https://www.oracle.com/kr/database/technologies/appdev/jdbc-downloads.html
>   * https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8
>
>   



## 실행 방법

```bash
> gradle clean appRun
```

이후 브라우저에서 아래 주소로 접속

* 예제 URL
  * `http://localhost:8090/pro07/member`





## 의견

* 



## 기타

#### LocalDateTime `<-> `Date 간 변환

```java
// ResultSet의 getTimestamp()로 받아온 Date를 LocalDateTime으로 변환
// 시간 값이 포함됨.
LocalDateTime joinDate = rs.getTimestamp("joinDate").toLocalDateTime(); 

// rs.getDate()로 가져온 값은 시간 정보가 없음.
```

```java
// LocalDateTime -> Date 변환은...
Date date = java.sql.Timestamp.valueOf(localDateTime);
```



#### Tomcat으로 DataSource를 생성하고 JNDI로 접속하는 것을 Gretty 환경에서 어떻게 할까?

* `${projectRoot}/webapp-tomcat` 경로에 context.xml 파일을 만들고 내용을 다음과 같이 적는다.

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <Context>
    <WatchedResource>WEB-INF/web.xml</WatchedResource>
    <Resource
      name="jdbc/oracle" 
      auth="Container" 
      type="javax.sql.DataSource" 
      driverClassName="oracle.jdbc.OracleDriver" 
      url="jdbc:oracle:thin:@localvmdb.oracle_xe_18c:1521:XE" 
      username="scott" 
      password="tiger"
      maxTotal="50"
      maxWaitMillis="-1"
      closeMethod="close" 
    />
  </Context>
  
  ```

* build.gradle 설정 수정

  ```groovy
  dependencies {
    implementation "com.oracle.database.jdbc:ojdbc8:${ojdbcVersion}"
    ...
    // gretty "com.oracle.database.jdbc:ojdbc8:${ojdbcVersion}" // implementation으로 있다면 필요없음
    gretty "org.apache.tomcat:tomcat-dbcp:${tomcat10Version}"
    ...
  }
  
  gretty {
    ...
    contextConfigFile = 'context.xml'
    enableNaming = true
    ...
  }
  
  ```

  * dependencies 속성에 gretty 설정으로 tomcat-dbcp를 디펜던시 한다.
  * Gretty 설정에 enableNaming 를 true로 설정해 Naming 기능을 활성화하고, context.xml 파일 이름을 맞춰준다.



결국 설정은 잘되었다... 

그런데 남은 문제는 테스트를 어떻게 해야하는지?



##### ✨ 참고 링크

* https://akhikhl.github.io/gretty-doc/tomcat-context.xml-support.html
* https://github.com/akhikhl/gretty/issues/427



#### tomcat dbcp 경고들...

```
경고: Name = oracle Property maxActive is not used in DBCP2, use maxTotal instead. maxTotal default value is 8. You have set value of "50" for "maxActive" property, which is being ignored.
2월 27, 2023 12:50:29 오전 java.util.ArrayList forEach
경고: Name = oracle Property maxWait is not used in DBCP2 , use maxWaitMillis instead. maxWaitMillis default value is PT-0.001S. You have set value of "-1" for "maxWait" property, which is being ignored.
2월 27, 2023 12:50:29 오전 org.apache.jasper.servlet.TldScanner scanJars
정보: At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
```

* DBCP2가 되면서.. 프로퍼티 명이 바뀌어서 그런 것 같다.. 일단 이름만 바꿔줌.
  * maxActive `->` maxTotal
  * maxWait `->` maxWaitMillis 





## JNDI 테스트

테스트할 때는 JNDI 환경을 만들어두고, 데이터소스는 DriverManagerSource 같은 것을 사용하는 걸로...

* https://www.javadoc.io/doc/org.springframework/spring-test/5.2.5.RELEASE/org/springframework/mock/jndi/SimpleNamingContextBuilder.html
  * Spring 5.2까지는 `SimpleNamingContextBuilder`가 있었는데.. Depreacted 되었고, 6에서는 아예없다.
  * Simple-JNDI를 사용하라고함.
    * https://github.com/h-thurow/Simple-JNDI



### 적용방법

1. simple-jndi 라이브러리 디펜던시 추가

   ```groovy
   testImplementation "com.github.h-thurow:simple-jndi:${simpleJndiVersion}"
   ```

2. src/test/resources 경로에 jndi.properties 파일 아래 내용으로 추가

   ```properties
   java.naming.factory.initial=org.osjava.sj.SimpleContextFactory
   org.osjava.sj.jndi.shared=true
   org.osjava.sj.delimiter=.
   jndi.syntax.separator=/
   org.osjava.sj.space=java:/comp/env
   org.osjava.sj.root=src/test/resources/jndi
   ```

3. src/test/resources/jndi 경로 만들어서 jdbc.properties 파일 추가

   ```properties
   oracle.type=javax.sql.DataSource
   oracle.driver=oracle.jdbc.OracleDriver
   oracle.url=jdbc:oracle:thin:@localvmdb.oracle_xe_18c:1521:XE
   oracle.user=scott
   oracle.password=tiger
   ```

   * 이 프로퍼티 파일명을 jndi접근시 . 앞부분 경로명으로 해주면 된다.

     

아주 신기하게 잘되길레... 봤더니..  simple-jndi의 데이터소스 클래스를 사용한다. 😄

`org.osjava.datasource.SJDataSource`



#### 참조

* https://www.baeldung.com/spring-mock-jndi-datasource
