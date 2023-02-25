# DB 스키마 생성문 모음

> 각장에서 만들었던 테이블 생성문은 이 문서에다 모아두자!
>
> 접속 도구는 ... SQL Developer와 DBeaver 둘다 사용할 것 같다..😄



## 7장. 서블릿 비즈니스 로직 처리

### 코드 7 -1 회원정보를 저장하는 테이블을 생성하고 추가하는 SQL 문

```sql
-- 회원 정보를 저장하는 테이블을 생성하고 추가하는 SQL문
CREATE TABLE t_member(
  id         VARCHAR2(10) PRIMARY KEY,
  pwd        VARCHAR2(10),
  name       VARCHAR2(50),
  email      VARCHAR2(50),
  joinDate   DATE DEFAULT SYSDATE
);

INSERT INTO t_member
  VALUES('hong', '1212', '홍길동', 'hong@gamil.com', TO_DATE('2023-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member
  VALUES('lee', '1212', '이순신', 'lee@test.com', TO_DATE('2023-02-02 11:00:00', 'YYYY-MM-DD HH24:MI:SS'));
  
INSERT INTO t_member
  VALUES('kim', '1212', '김유신', 'hong@gamil.com', TO_DATE('2023-02-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));
  
COMMIT;
```



* 



