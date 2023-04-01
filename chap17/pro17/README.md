# 17장 모델2 방식으로 효율적으로 개발하기

> * ...
> * 17장 부터는 내용이 많다. 답변형 게시판 구현하기가 좀 난이도가 있는 것 같음.. 😅 잘 따라해보자.
> 

## 실행 방법

* Gretty 실행
  ```bash
  > gradle clean appRun
  ```
  
  

이후 브라우저에서 아래 주소로 접속

* 예제 URL 목록 정리
  * http://localhost:8090/pro17/index.html



## 의견

* tomcat-jdbc가 보여서 뭔지 챗봇 에게 물어봤는데 아래 답변을 받음... 그냥 궁금해서 써봄..

  ```
  tomcat-jdbc와 tomcat-dbcp는 모두 Tomcat에서 제공하는 데이터베이스 연결 풀입니다. tomcat-dbcp는 Apache Commons DBCP를 기반으로 하며 Tomcat의 기본 연결 풀 전략으로 사용됩니다. 반면 tomcat-jdbc는 Apache Commons DBCP의 대체품으로 제공됩니다tomcat-jdbc는 고도의 동시성을 지원하며 jdbcInterceptors와 같은 추가 매개 변수를 제공합니다1. 또한 tomcat-jdbc는 다중 스레드 환경에서 Commons DBCP 1.x보다 성능이 뛰어납니다2. 어떤 것을 사용할지는 여러분의 요구 사항에 따라 결정하시면 됩니다.
  ```

* context.xml의 JNDI 설정

  ```
      기존 ==>
      type="javax.sql.DataSource"
      maxTotal="50"
      maxWaitMillis="-1"
      
      변경 ===>     
      type="org.apache.tomcat.jdbc.pool.DataSource"
      factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
      maxActive="50"
      maxWait="-1"
  ```

* gretty 디펜던시 설정

  ```groovy
  // 기존
  grettyRunnerTomcat10 "org.apache.tomcat:tomcat-dbcp:${tomcat10Version}"
  // 변경
  grettyRunnerTomcat10 "org.apache.tomcat:tomcat-jdbc:${tomcat10Version}"
  ```

이렇게 바꿨을 때 잘 동작한다. tomcat-dbcp는 속성을 잘못적으면 경고가 나왔는데, tomcat-jdbc는 그냥 무시하나보다 아무렇게나 적었을 때.. 경고가 안나옴.





## 기타

* ... 
