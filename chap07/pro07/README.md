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



