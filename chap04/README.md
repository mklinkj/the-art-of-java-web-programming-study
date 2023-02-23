# 4장 웹 애플리케이션 이해하기

> 이번 장 예제를 Gradle + Gretty 프로젝트로 바꿔볼까 했는데.. ✨ 기본을 알아야하니... 이 부분은 그대로 따라하는 것이 좋을 것 같다.



## 목차

### 4.1. 웹 애플리케이션 

###  4.2. 웹 애플리케이션 기본 구조

* https://youtu.be/YW7cwYHf8Bk

  



## 진행

* ....



## 의견

* 

  

## 정오표

* 없음.





## Tomcat  10.1 관련 ...

### JAVA_HOME 설정

* `${TOMCAT_HOME}/bin` 경로에 `setenv.bat` 파일을 만들고 아래 내용을 설정해서도 JAVA_HOME을 설정할 수 있다.

  ```bat
  SET JAVA_HOME=C:/JDK/17

### HTTP /1.1 포트 변경

`${TOMCAT_HOME}/bin` 경로의 server.xml 파일 내용 중,  HTTP/1.1 커넥터의 port 값 8080을 8090으로 바꾼다. 

```xml
    <!-- A "Connector" represents an endpoint by which requests are received
         and responses are returned. Documentation at :
         HTTP Connector: /docs/config/http.html
         AJP  Connector: /docs/config/ajp.html
         Define a non-SSL/TLS HTTP/1.1 Connector on port 8080
    -->
    <Connector URIEncoding="UTF-8" 
               port="8090" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

* ` URIEncoding="UTF-8"` 설정도 일단 추가해두자.


### 로깅 한글 설정

* 로깅출력이 UTF-8이 기본인데, 현재 사용중인 기본 터미널을 Windows Terminal로 바꿔놔서 레지스트리에서 CodePage 값추가하는 방법이 적용되지 않는다.

* `${TOMCAT_HOME}/bin` 경로의 logging.properties 내용 중 ConsoleHandler의 encoding 값을 아래와 같이 EUC-KR로 바꾼다.

  ```properties
  java.util.logging.ConsoleHandler.encoding = EUC-KR
  ```



### 관리자 PW 설정

3장의 개발환경 설정하기에서 Tomcat의 관리자를 설정하는 부분이 있었다. 나는 그냥 Windows x64용 바이너리 압축을 풀기만해서..  

```xml

  <user username="admin" password="1234" roles="manager-gui"/>
<!--
  <user username="robot" password="<must-be-changed>" roles="manager-script"/>
-->
```

`http://localhost:8090/manager/html` 페이지를 따로볼 것 같지는 않은데... 로그인 되게 설정은 해두자..

