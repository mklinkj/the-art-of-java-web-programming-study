# 16장 HTML5와 제이쿼리



> * jQuery 사용 예제는 최신 ES6이상 자바스크립트 코드를 사용하자.
> * JSON 라이브러리는 [`json-simple`](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple) 대신, 현시점에 보편적인 [`jackson-databind`](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) 를 사용하도록 하자.
> * 예제 프로젝트: [pro16](pro16)



## 16.1 HTML5 주요 개념

* ...
  

## 16.2 HTML5 시맨틱 웹을 위한 구성 요소

* ...

### 16.2.1 HTML5에 추가된 웹 페이지 구조 관련 태그

* ...

### 16.2.2 HTML5 웹 페이지 구조 관련 태그 사용

* ...



## 16.3 제이쿼리 주요 개념

* ...

* webjars로 한번 써볼까해서 넣어봤는데...

  * https://www.webjars.org/documentation#servlet3

  * https://www.webjars.org/documentation#servlet2

  * 지금 서블릿 3.0이상이기 때문에 특별히 추가할 것은..

    ```groovy
    implementation 'org.webjars:jquery:3.6.3'
    ```

    위의 내용만 추가하고 사용처에서는 단지... 아래 내용만 추가해주면 되는 것 같다.

    ```html
    <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
    ```

    버전을 빼는 방법을 해보려했는데.. 잘안된다... locator를 추가해도 잘 안되는 듯..😓

  






---

## 의견

* ...

  


## 정오표

* ...
  
  

## 기타

* ...