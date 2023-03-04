# 3장 개발환경 설정하기

>  개발환경은 대부분 준비가 되어있어서 필요시 ERD 관리 툴정도만 설치하게 될 것 같은데.. 일단 한번 봐보자..🎈
>
>  exERD 평가판 기간이 짧아서 Visual Paradigm 무료버전 대신 쓰면 될 것 같다..😉



## 목차

### 3.1. JDK 설치하기

* https://youtu.be/COe2wsDHPv4
* Java 10의 설치가 가이드 되는데.. 이미 Java 17로 환경이 준비되어있어서 넘어가자.



### 3.2 JDK 환경변수 설정하기

* https://youtu.be/JeEZYaXWw78
* 시스템 글로벌로 JAVA_HOME 설정하지 않고, JAVA DEV 명령 프롬프트 탐색기에 연동해서 사용하고 있어서 이부분도 그냥 넘어감.



### 3.3 톰캣 컨테이너 설치하기

* https://youtu.be/JeEZYaXWw78

* 가능한 Gretty를 통해서 Tomcat을 실행할 것이여서 내용만 봐보자..  책에서는 Tomcat 9.0.6을 설치하라고 가이드 되었다.

* 가이드중 특이한 점이... HTTP포트를 8080 인것을 8090으로 바꾸라는 부분이 있음.

* Tomcat Admin 설정관련은 필요시 Gretty 참고해보자.

* 4장 먼저 잠깐 봤는데.. Tomcat 은 war 배포 연습이 있어서 이건 설치해두자..✨

  



### 3.4 이클립스 IDE 설치하기

* https://youtu.be/LxQDbf2NoU0
* STS나 eclipse관련은 이제 `전자정부 프레임워크 IDE`이외에는 다 삭제해버리고 더 이상 쓰지 않기로해서.. 넘어가자.
  * 오래 사용해오면서 스트레스 받을 일들이 종종 있었음.
  * ✨ 그런데.. 잠시 간과한점이... Visual Studio Code를 사용한다고 해도 Java확장 플러그인이 eclipse 기반없긴함. 😅 많이 좋아지는 중인 것 같긴하지만...



### 3.5 Java EE API 문서 즐겨찾기에 추가하기

* https://youtu.be/oMrfhBvdij4
* Oracle
  * https://javaee.github.io/javaee-spec/javadocs/
* Jakarta EE 9 
  * https://jakarta.ee/specifications/platform/9/apidocs/

이상한게 Jakarta EE API 페이지는 프레임구조라고 브라우저에서 차단되는 것 같은데.. 그런데 왜 Oracle 쪽은 잘되지? 😓



### 3.6 비주얼 스튜디오 코드 설치하기

* https://youtu.be/7GhM_6mMHu4
* VSCode는 설치되어있음.



### 3.7 Oracle DBMS 설치하기

* https://youtu.be/mJXgnqaGR8o

* 도커로 설치해서 사용하고 있음. OracleXE 11g R2, XE 18c 둘 다 사용가능한 상태이긴한데..

  * 그런데 11g R2의 경우는 DB 문자집합을 UTF-8에서 MS949로 바꾼상태임.
  * 상황에 따라 둘 중 하나 사용하자.. 왠지 지금 상황에서는 18c 사용하는 것이 나을듯...😑

* 책 내용에서도 별다른 인코딩 설정없이 기본으로 진행하신 것 같다.

* sqlplus 접속 테스트 (XE 18c에서 확인)

  ```
  bash-4.4$ sqlplus
  
  SQL*Plus: Release 18.0.0.0.0 - Production on Thu xxx xx xx:xx:xx xxxx
  Version 18.4.0.0.0
  
  Copyright (c) 1982, 2018, Oracle.  All rights reserved.
  
  Enter user-name: system
  Enter password:
  
  Connected to:
  Oracle Database 18c Express Edition Release 18.0.0.0.0 - Production
  Version 18.4.0.0.0
  
  SQL> CREATE USER scott IDENTIFIED BY tiger;  -- 계정 생성
  
  User created.
  
  SQL> GRANT RESOURCE, CONNECT TO scott;   -- 권한 부여
  
  Grant succeeded.
  
  SQL>
  
  -- 이후 scott으로 접속 확인
  ```

  * 사용자 생성시 18c에서 아래와 같은 오류가 발생하면..
  
    * `ORA-65096: 공통 사용자 또는 롤 이름이 부적합합니다.`
  
    ```sql
    ALTER SESSION SET "_ORACLE_SCRIPT"=true;
    ```
  
    위의 명령을 설정한 뒤에 실행해주도록 하자~
  
  * 18c에서는 테이블 스페이스에 대한 공간할당 권한을 정해줘야했다.
  
    ```sql
    ALTER USER scott DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;
    ```
  
    이 설정을 하지 않았을 때, 테이블은 만들 수는 있었으나 INSERT가 안됨.
    

### 3.8 SQL Developer 설치하기

* https://youtu.be/ShAQJkg06Fc

* DBeaver에 scott, SYSTEM에 대해 연결 설정해서 확인해봄

  * SQL Developer의 고유 기능을 쓰면 그때 설치해보자.

  

  

### 3.9 exERD 설치하기

* https://youtu.be/OCYB1DPFpK8

* 일단 이클립스를 사용하지 않기 때문에... 사용하지 않도록 하고,  [Visual Paradigm](https://www.visual-paradigm.com/) 무료버전이나 다른 eRD툴 써야겠다. 

  * MySQL Workbench에 내장된 ERD 도구도 잘 썼었는데...😓 Oracle에서는 기본으로 제공을 안해주네...

  



## 진행

* ....



## 의견

* 좀 다르게 사용하는 부분도 있지만 별로 문제 없을 것 같다.

  

## 정오표

* 없음.