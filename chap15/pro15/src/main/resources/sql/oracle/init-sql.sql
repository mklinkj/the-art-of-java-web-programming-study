DROP TABLE t_member;

CREATE TABLE t_member
(
    id       VARCHAR2(10) PRIMARY KEY,
    pwd      VARCHAR2(10),
    name     VARCHAR2(50),
    email    VARCHAR2(50),
    joinDate DATE DEFAULT SYSDATE
);

INSERT INTO t_member
VALUES ('mklinkj', '1234', '정션링크', 'mklinkj@github.com',
        TO_DATE('2023-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member
VALUES ('hong', '1212', '홍길동', 'hong@gamil.com',
        TO_DATE('2023-02-02 11:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member
VALUES ('lee', '1212', '이순신', 'lee@test.com',
        TO_DATE('2023-02-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member
VALUES ('kim', '1212', '김유신', 'hong@gamil.com',
        TO_DATE('2023-02-04 13:00:00', 'YYYY-MM-DD HH24:MI:SS'));