DROP TABLE t30_member;

CREATE TABLE t30_member
(
    id        VARCHAR2(10) PRIMARY KEY,
    pwd       VARCHAR2(200) NOT NULL,
    name      VARCHAR2(50) NOT NULL,
    email     VARCHAR2(50) NOT NULL,
    join_date DATE DEFAULT SYSDATE NOT NULL
);

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('mklinkj', '{noop}1234', '정션링크', 'mklinkj@github.com', TO_DATE('2023-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('hong', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '홍길동', 'hong@gamil.com', TO_DATE('2023-02-02 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('lee', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '이순신', 'lee@test.com', TO_DATE('2023-02-03 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('kim', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '김유신', 'hong@gamil.com', TO_DATE('2023-02-04 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('choi', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '최치원', 'choi@gamil.com', TO_DATE('2023-02-05 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
