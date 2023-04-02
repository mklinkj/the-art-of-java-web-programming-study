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



### 17.4.1 게시판 글 목록 보기 구현

* ...

* Oracle 계층 쿼리

  ```sql
  SELECT LEVEL
       , article_no
       , parent_no
       , LPAD(' ', 4 * (LEVEL-1)) || title AS title
       , content
       , write_date
       , id
    FROM t17_board
   START WITH parent_no = 0
   CONNECT BY PRIOR article_no = parent_no
   ORDER SIBLINGS BY article_no DESC;
  ```

  결과는 잘 나오는데, 의미는 대략적으로 알겠다.. 😅

  * `START WITH parent_no = 0`
    * 계층형 구조에서 최상위 계층의 ROW를 식별하는 조건 명시
  * ` CONNECT BY PRIOR article_no = parent_no`
    * 계층 구조가 어떤 식으로 연결되는지를 기술하는 부분
  * ` ORDER SIBLINGS BY article_no DESC`
    * 동일한 부모를 가진 형제들 기준으로 내림차순 정렬
    * 계층구조는 그대로 유지하면서 동일 부모를 가진 자식들끼리의 정렬 기준을 주는 것

* 아래는 AI 한태 물어봄 위에거, MySQL 쿼리로 바꿀 수 없는지?

  ```sql
  WITH RECURSIVE cte (level, article_no, parent_no, title, content, write_date, id) AS (
      SELECT 1, article_no, parent_no, CONCAT(REPEAT(' ', 4 * (1-1)), title) AS title, content, write_date, id
      FROM t17_board
      WHERE parent_no = 0
      UNION ALL
      SELECT cte.level + 1, t17_board.article_no, t17_board.parent_no,
             CONCAT(REPEAT(' ', 4 * (cte.level)), t17_board.title) AS title,
             t17_board.content,
             t17_board.write_date,
             t17_board.id
      FROM t17_board JOIN cte ON cte.article_no = t17_board.parent_no
  )
  SELECT * FROM cte
  ORDER BY parent_no, article_no DESC;
  ```

  - [ ] 내가 확인을 해서 정렬 보정해야될 것 같음.

#### MySQL 용 데이터 입력

```sql
CREATE TABLE t17_member
(
    id        VARCHAR(10) PRIMARY KEY,
    pwd       VARCHAR(100) NOT NULL,
    name      VARCHAR(50) NOT NULL ,
    email     VARCHAR(50) NOT NULL ,
    join_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('mklinkj', '1234', '정션링크', 'mklinkj@github.com','2023-02-01 10:00:00');

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('hong', '1212', '홍길동', 'hong@gamil.com','2023-02-02 11:00:00');

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('lee', '1212', '이순신', 'lee@test.com','2023-02-03 12:00:00');

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('kim', '1212', '김유신', 'hong@gamil.com', '2023-02-04 13:00:00');

CREATE TABLE t17_board
(
    article_no          INTEGER(10) PRIMARY KEY,
    parent_no           INTEGER(10) DEFAULT 0,
    title               VARCHAR(500) NOT NULL,
    content             VARCHAR(4000),
    image_file_name     VARCHAR(100),
    write_date          DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id                  VARCHAR(10),
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES t17_member(id)
);


INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (1, 0, '테스트글입니다.', '테스트글입니다.', null, '2023-04-03 12:00:00', 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (2, 0, '안녕하세요.', '상품 후기 입니다.', null, '2023-04-03 13:00:00', 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (3, 2, '답변입니다..', '상품 후기에 대한 답변입니다.', null, '2023-04-03 14:00:00', 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (5, 3, '답변입니다..', '상품 좋습니다.', null, '2023-04-03 14:30:00', 'lee');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (4, 0, '김유신입니다.', '김유신 테스트 글입니다.', null, '2023-04-03 14:40:00', 'kim');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (6, 2, '상품 후기입니다..', '이순신씨의 상품 사용 후기를 올립니다!!.', null, '2023-04-03 15:00:00', 'lee');

```

* MySQL에 데이터는 위와 같이넣었었는데... LPAD 처리에서 문제가 있고, 내가아직 잘 몰라서 이건 17장을 다 진행하고 해봐야겠다.
  * [ ] 17장 완료 후...
  * [ ] Oracle 계층 쿼리를 잘 이해하게 되었을 때.. 다시 해보기.. 😅



### UI관련해서는...

* 부트스트랩으로 적용하고 있는데.. 있는 클래스 가지고 잘 쓰니 코드가 단순해지고 있다. 😄
  * https://getbootstrap.com/docs/4.0/utilities/spacing/
  * `<span class="m-${article.level}"></span>` 빈 여백도 이렇게 주면 됨.






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