# 7장 서블릿 비즈니스 로직 처리

> * 이번 장은 DB연결이 있던데... JNDI로 Tomcat에 설정한 DateSource에 연결하는 주제가 있던데.. Gretty에서 잘 설정할 수 있을지? 
> * 예제 프로젝트: [pro07](pro07)



## 목차

### 7.1 서블릿의 비즈니스 로직 처리 방법

* ...



### 7.2 서블릣의 데이터베이스 연동하기

* ...

#### 7.2.1 서블릿으로 회원 정보 테이블의 회원 정보 조회

* ...

* 드라이버 배치
  * `WEB-INF/lib` 경로에 ojdbc.jar를 배치 해야하는데.. 여기서는 Gradle이 디펜던시를 관리해주므로 build.gradle에 추가만 해주면 된다.
  
    ```groovy
    dependencies {
      ...
      implementation "com.oracle.database.jdbc:ojdbc8:${ojdbcVersion}"
      ...
    }      
    ```
  
    * ojdbc8로 사용해보자..
      

#### 7.2.2 PreparedStatement를 이용한 회원정보 실습

* ...
* 이상없이 잘되었다.
* 가입일을 그냥 시간없는 날짜만 저장하는 식으로 해도 되었는데, LocalDateTime을 사용하고 싶어서 시간값까지 전부 넣어서 처리했다.
* 실제 환경에서는 회원의 가입시간까지는 그렇게 중요하지 않을 것 같긴함..😅














## 의견

* 

  

## 정오표

* 



## 기타

* ContextPath를 `/`로 사용하다가  `/${projectName}` 으로 사용하고 있어서 favicon 처리가 힘들다..😅 
  * 일단 `favicon.ico` 파일은 지워놔야겠다. 이건 앞단에 HTTP WEB 서버를 두고 따로 처리해야할 것 같은데...
