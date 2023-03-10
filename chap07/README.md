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



### 7.3 DataSource 이용해 데이터베이스 연동하기

* ...

* 커넥션 풀 (Connection Pool) 

  * 미리 데이터베이스와 연결시킨 상태를 유지하는 기술

  

#### 7.3.1 커넥션 풀 동작 과정

* ...



#### 7.3.2 JNDI

* ...
* JNDI (Java Naming and Directory Interface)
  * 필요한 자원을 키/값 쌍으로 저장한 후 필요할 때 키를 이용해 값을 얻는 방법



#### 7.3.3 톰캣의 DataSource 설정 및 사용 방법

* ...

  

#### 7.3.4 이클립스에서 톰캣 DataSource 설정

* ...



#### 7.3.5 톰캣의 DataSource로 연동해 회원 정보 조회 실습

* ...



### 7.4 DataSource 이용해 회원 정보 등록하기

* ...
* 회원가입 추가이므로 MemberServlet 만 다른 패키지에 만들고, DAO하고 VO는 중복이니 복사하진 않아도 되겠다. 



### 7.5 회원 정보 삭제하기

* ...
* 삭제를 단순 GET으로 처리하지 않고 폼 POST전송으로 좀 더 보강했다.




## 의견

* 무사히 Greety 환경에서 JNDI 설정도 할 수 있었고, 여러모로 재미있었다... 👍 

  

## 정오표

* 없음.



## 기타

* ContextPath를 `/`로 사용하다가  `/${projectName}` 으로 사용하고 있어서 favicon 처리가 힘들다..😅 
  * 일단 `favicon.ico` 파일은 지워놔야겠다. 이건 앞단에 HTTP WEB 서버를 두고 따로 처리해야할 것 같은데...

* 이 프로젝트에서는 테스트할 때.. 트랜젝션 롤백을 어떻게 해야할지?
  * Spring이면 어노테이션 붙이면 되긴하는데... 어떻게 해야할지 잘모르겠다.
    * DataSource로부터 Connection을 얻은 이후 그 커넥션에 대한 setAutoCommit 설정을 false로 주면 될 것 같은데.. 현재 코드로는 어떻게 할 수가 없음.. 

