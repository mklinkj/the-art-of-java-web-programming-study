# 20장 스프링 AOP 기능

> * ...
> 
> 

## DB전환

web.xml에서 설정 파일을 무엇으로 하느냐로 선택되게 함.

```xml
  <!-- MySQL / Oracle 전환은 web.xml에서 설정 파일을 무엇을 읽느냐로 결정하자. -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:application-context-oracle.xml</param-value>
    <!--<param-value>classpath:application-context-mysql.xml</param-value>-->
  </context-param>
```



## 실행 방법

* Gretty 실행
  ```bash
  > gradle clean appRun
  ```
  
  

이후 브라우저에서 아래 주소로 접속

* 예제 URL 목록 정리
  * http://localhost:8090/pro20/index.html



## 의견

* ...






## 기타

* ... 
