DROP TABLE t_member;
DROP TABLE t17_board;
DROP TABLE t17_member;

CREATE TABLE t_member
(
    id       VARCHAR2(10) PRIMARY KEY,
    pwd      VARCHAR2(10),
    name     VARCHAR2(50),
    email    VARCHAR2(50),
    joinDate DATE DEFAULT SYSDATE
);

INSERT INTO t_member (id, pwd, name, email, joinDate)
VALUES ('mklinkj', '1234', '정션링크', 'mklinkj@github.com',
        TO_DATE('2023-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member (id, pwd, name, email, joinDate)
VALUES ('hong', '1212', '홍길동', 'hong@gamil.com',
        TO_DATE('2023-02-02 11:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member (id, pwd, name, email, joinDate)
VALUES ('lee', '1212', '이순신', 'lee@test.com',
        TO_DATE('2023-02-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t_member (id, pwd, name, email, joinDate)
VALUES ('kim', '1212', '김유신', 'hong@gamil.com',
        TO_DATE('2023-02-04 13:00:00', 'YYYY-MM-DD HH24:MI:SS'));


-- ### 17장 게시판 ###


CREATE TABLE t17_member
(
    id        VARCHAR2(10) PRIMARY KEY,
    pwd       VARCHAR2(100) NOT NULL,
    name      VARCHAR2(50) NOT NULL ,
    email     VARCHAR2(50) NOT NULL ,
    join_date DATE DEFAULT SYSDATE NOT NULL
);

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('mklinkj', '1234', '정션링크', 'mklinkj@github.com',
        TO_DATE('2023-02-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('hong', '1212', '홍길동', 'hong@gamil.com',
        TO_DATE('2023-02-02 11:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('lee', '1212', '이순신', 'lee@test.com',
        TO_DATE('2023-02-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO t17_member (id, pwd, name, email, join_date)
VALUES ('kim', '1212', '김유신', 'hong@gamil.com',
        TO_DATE('2023-02-04 13:00:00', 'YYYY-MM-DD HH24:MI:SS'));

CREATE TABLE t17_board
(
    article_no          NUMBER(10) PRIMARY KEY,
    parent_no           NUMBER(10) DEFAULT 0,
    title               VARCHAR2(500) NOT NULL,
    content             VARCHAR2(4000),
    image_file_name     VARCHAR2(100),
    write_date          DATE DEFAULT SYSDATE NOT NULL,
    id                  VARCHAR2(10),
    CONSTRAINT FK_ID FOREIGN KEY(id)
    REFERENCES t17_member(id)
);


INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (1, 0, '테스트글입니다.', '테스트글입니다.', null, TO_DATE('2023-04-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (2, 0, '안녕하세요.', '상품 후기 입니다.', null, TO_DATE('2023-04-03 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (3, 2, '답변입니다..', '상품 후기에 대한 답변입니다.', null, TO_DATE('2023-04-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (5, 3, '답변입니다..', '상품 좋습니다.', null, TO_DATE('2023-04-03 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'lee');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (4, 0, '김유신입니다.', '김유신 테스트 글입니다.', null, TO_DATE('2023-04-03 14:40:00', 'YYYY-MM-DD HH24:MI:SS'), 'kim');

INSERT INTO t17_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (6, 2, '상품 후기입니다..', '이순신씨의 상품 사용 후기를 올립니다!!.', null, TO_DATE('2023-04-03 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'lee');

