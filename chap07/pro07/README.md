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
