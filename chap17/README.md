# 17장 모델2 방식으로 효율적으로 개발하기



> * 답글 구현에 저자님께서 진행하신 Oracle 계층형 쿼리로도 해보고,  MySQL 또는 HSQLDB에서도 진행을 별도해보자..
>   * Oracle 계층형 쿼리를 이해를 하는 것이 좋을 것 같은데...
>   * DB 쿼리로 가능하면 DB쿼리로 사용하는 것이 낫겠다. 내가 좀 왠만한 건 Java 단의 프로그래밍 처리가 무조건 낫다는 약간의 고정관념이 있긴한데... 생각을 유연하게 할 필요가 있음.
>   * 매퍼인터페이스 위에 쿼리적던 것을 매퍼 XML을 사용하도록 설정을 바꿨다.
>     * 나중에 동적 쿼리사용할 때는 Mapper XML 사용하는 것이 훨씬 나음.
> * 예제 프로젝트: [pro17](pro17)



## 17.1 웹 애플리케이션 모델

* ...

### 17.1.1 모델 1 방식

* ...

### 17.2.2 모델 2 방식



## 17.2 MVC 디자인 패턴

* ...

### 17.2.1 MVC 구성 요소와 기능

* ...



## 17.3 MVC를 이용한 회원 관리

* ...

### 17.3.1 회원 정보 조회 구현

* ...

* 부트 스트랩을 적용했는데, 생산성이 좋아지고 있다.
  * https://getbootstrap.com/docs/5.2/getting-started/introduction/
  * 빙 챗봇에 물어가면서 하니까 효율이 아주 좋음 👍, 질문의 이해를 아주 잘함 😄



### 17.3.2 회원 정보 추가 기능 구현

* ...
* 통합해서 사용할까 했는데.. 저자님 하시는대로 본리는 하는데, MemberController만 분리하자!
  * 중복 많아짐.. 😅
  * 그래도 뷰 파일들은 분리해보자..
* 회원 가입 페이지도 부트스트랩 적용해봤는데 괜찮아 보임..😅



### 17.3.3 회원 정보 수정 및 삭제 기능 구현

* ...
* Spring의 FlashAttribute 비슷하게 구현해서 추가
* 결과 alert에 대해 BootStrap의 modal로 적용
* https://getbootstrap.com/docs/5.2/forms/form-control/#readonly
  * 5.2 환경에서 readonly의 배경 색은 흰색임.



## 17.4 모델2로 답변형 게시판 구현하기

* ...




#### 물리 모델

* Visual Paradigm으로 그리긴 했는데, 여기는 타입이 고정되어있다. SQL 정식 타입만 사용할 수 있는 듯, 대략적으로 맞춰서 그렸다.

  ![20230403043310620](doc-resources\image-20230403043310620.png)

  * exERD를 사용하면 좋은데, 요즘은 개인 사용자 프리라이선스를 안준다. (2019년 1월 1일 부터)

  * DB컬럼명이 정리가 안되어있어서 컬럼명들을 스네이크 케이스로 이름을 바꿨다.. 

  * myBatis 옵션에서 `mapUnderscoreToCamelCase`는 설정해봄.. 기본 값은 false인듯한데, 기존 코드에도 영향은 없을 것 같다.

    ```java
    configuration.setMapUnderscoreToCamelCase(true);
    ```

* 트랜젝션이 언급되었는데, Connection에 대해 AutoCommit false로 하고 여러개 쿼리 실행시키고 commit 하는 부분은 아직 없을 것 같은데... 진행해보면서 확인해보자..




---

## 의견

* ...
  



## 정오표

* 695쪽 표에는 NOT NULL인데, 697에는 NOT NULL이 지정되지 않은 컬럼 들이 있다.

  * parentNO

  * content

  * id 

    


## 기타

* ✨✨✨ 중요한 변경인데...  MockServletContext를 생성할 때.. 컨텍스트 패스를 넣어생성해야 실행환경과 같아진다.

  ```java
  private MockServletContext createMockServletContext() {
    return new MockServletContext(getContextPath());
  }
  ```

  * 이전 프로젝트 다바꿔야하는데.... 😂😂😂, 이전껀 둿다가 천천히 바꾸자..