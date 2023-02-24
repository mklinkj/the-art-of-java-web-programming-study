# 4장 웹 애플리케이션 이해하기

> 이번 장 예제를 Gradle + Gretty 프로젝트로 바꿔볼까 했는데.. ✨ 기본을 알아야하니... 이 부분은 그대로 따라하는 것이 좋을 것 같다.



## 목차

### 4.1. 웹 애플리케이션 

###  4.2. 웹 애플리케이션 기본 구조

* https://youtu.be/YW7cwYHf8Bk




### 4.3 컨테이너에서 웹 애플리케이션 실행하기

* https://youtu.be/WZxRUB1-5ws
* https://youtu.be/cMknM795h6s

#### 4.3.1 컨테이너에 웹 애플리케이션 등록 ~ 4.3.3

* [webShop/main.html](webShop/main.html)
  * 그냥 리액트 HTML 파일로 만들어 넣어봤음. 😅
  * [webShop](webShop) 경로를 `mklink /j` 명령으로 Tomcat의 `webapp/webShop` 경로로 정션 링크 시킴
* Tomcat 실행뒤 접속 주소
  * `http://localhost:8090/webShop/main.html`



#### 4.3.4 컨텍스트란?

* https://youtu.be/yzqdpZLqFmw
* server.xml에 등록하는 웹 어플리케이션을 컨텍스트라고 함.
* Tomcat에서 인식하는 한개의 어플리케이션



#### 4.3.5 톰캣 컨테이너에 컨텍스트 등록하기

* https://youtu.be/BMwBzR42f9k

`${TOMCAT_HOME}/conf/server.xml` 에 Host 태그 안쪽에 다음내용 추가

```xml
        <Context
          path="webMall"
          docBase="{webShop 디렉토리 경로}"
          reloadable="true" />
        />
```

* `http://localhost:8090/webMall/main.html` 경로로 잘 접속되는지 확인.



#### 4.3.6 톰캣 컨테이너에서의 웹 애플리케이션 동작과정

* ... 





## 4.4 이클립스에서 웹 애플리케이션 실습하기

> Eclipse는 사용하지 않을 예정이니.. 책 내용만 쭉 보자.. 😅

* https://youtu.be/bG8oR0SHRFs

  

### 4.4. 1이클립스에서 웹 프로젝트 생성

* 루트이하에 WebContent라고 HTML, JSP 파일 넣어둘 디렉토리를 그냥 만들어주는듯...

* 소스 구조를  다음과 같이 만들어주는 듯..

  ```
  webShop
    ├─ build
    ├─ src
    └─ WebContent
        │    main.html 
        ├─ META-INF
        └─ WEB-INF
             │   web.xml
             └─lib
  ```

  

#### 4.4.2 이클립스에서 HTML 파일 생성

* ...

#### 4.4.3 이클립스와 톰캣 연동

* ...

#### 4.4.4 이클립스와 연동한 톰캣에 프로젝트 등록

* ...

#### 4.4.5 웹 브라우저에서 요청하기

* ...



## 4.5 웹 애플리케이션 서비스 하기

* https://youtu.be/wbtFS_VETSg

### 4.5.1 톰캣에 배치하기

* 이클립스를 사용하지 않으니 war생성시 JDK에서 제공해주는 jar명령을 직접 실행하자..

  ```
  > cd webShop의_경로
  > jar -cvf webShop.war *
  ```

* 생성한 webShop.war를 ${TOMCAT_HOME}/webapps`로 복사하고 Tomcat 재시작

* `http://localhost:8090/webMall/main.html` 로 다시 접속해서 접속되는지 확인.



## 의견

* 그래도 한번 밑바닥 부터 조립해본 것 같아서 좋았다. 👍👍👍

  

## 정오표

* 없음.



## Eclipse를 사용하지 않음으로 인헤 별도로 해야할일들..

### war 압축 명령 실행.

```
>jar -cvf webShop.war *
Manifest를 추가함
추가하는 중: classes/(입력 = 0) (출력 = 0)(0%를 저장함)
추가하는 중: lib/(입력 = 0) (출력 = 0)(0%를 저장함)
추가하는 중: main.html(입력 = 811) (출력 = 385)(52%를 감소함)
추가하는 중: WEB-INF/(입력 = 0) (출력 = 0)(0%를 저장함)
추가하는 중: WEB-INF/web.xml(입력 = 320) (출력 = 177)(44%를 감소함)

>
```

* webShop.war란 이름으로 해당 디렉토리 내용을 zip압축하는 것과 큰 차이가 없음.



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

