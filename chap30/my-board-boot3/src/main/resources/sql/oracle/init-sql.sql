-- * 외래키 관계가 있어서 게시판 테이블이 먼저 삭제되야한다.
DROP TABLE t30_attach_file;
DROP TABLE t30_board;
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

INSERT INTO t30_member (id, pwd, name, email, join_date)
VALUES ('no_article', '{noop}1234', '글안씀', 'no_article@github.com', TO_DATE('2023-02-06 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE t30_board
(
    article_no      NUMBER(10) PRIMARY KEY,
    parent_no       NUMBER(10) DEFAULT 0,
    title           VARCHAR2(500) NOT NULL,
    content         VARCHAR2(4000),
    image_file_name VARCHAR2(100),
    write_date      DATE DEFAULT SYSDATE NOT NULL,
    id              VARCHAR2(10),
    CONSTRAINT FK_30_ID FOREIGN KEY (id)
        REFERENCES t30_member (id)
);


INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (1, 0, '테스트글입니다.', '테스트글입니다.', null, TO_DATE('2023-04-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (2, 0, '안녕하세요.', '상품 후기 입니다.', null, TO_DATE('2023-04-03 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (3, 2, '답변입니다..', '상품 후기에 대한 답변입니다.', null, TO_DATE('2023-04-03 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'hong');

INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (5, 3, '답변입니다..', '상품 좋습니다.', null, TO_DATE('2023-04-03 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'lee');

INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (4, 0, '김유신입니다.', '김유신 테스트 글입니다.', null, TO_DATE('2023-04-03 14:40:00', 'YYYY-MM-DD HH24:MI:SS'), 'kim');

INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
VALUES (6, 2, '상품 후기입니다..', '이순신씨의 상품 사용 후기를 올립니다!!.', null, TO_DATE('2023-04-03 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'lee');

/* 이 코드를 자동으로 넣어줄 수가 없다. */
/*
DECLARE
    i NUMBER := 7;
BEGIN
    WHILE(i <= 100)
        LOOP
            INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id)
            VALUES (i, 0, i||'테스트글입니다.', i||'테스트글입니다.', null, TO_DATE('2023-04-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'mklinkj');
            i := i + 1;
END LOOP;
END;
COMMIT;
*/
/* JDBC 연결로 바로 PL/SQL을 실행시킬 수 없어서, 생성된 데이터를 INSERT 쿼리로 만들어서 붙여넣었다. */
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (7, 0, '7테스트글입니다.', '7테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (8, 0, '8테스트글입니다.', '8테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (9, 0, '9테스트글입니다.', '9테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (10, 0, '10테스트글입니다.', '10테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (11, 0, '11테스트글입니다.', '11테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (12, 0, '12테스트글입니다.', '12테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (13, 0, '13테스트글입니다.', '13테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (14, 0, '14테스트글입니다.', '14테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (15, 0, '15테스트글입니다.', '15테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (16, 0, '16테스트글입니다.', '16테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (17, 0, '17테스트글입니다.', '17테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (18, 0, '18테스트글입니다.', '18테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (19, 0, '19테스트글입니다.', '19테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (20, 0, '20테스트글입니다.', '20테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (21, 0, '21테스트글입니다.', '21테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (22, 0, '22테스트글입니다.', '22테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (23, 0, '23테스트글입니다.', '23테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (24, 0, '24테스트글입니다.', '24테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (25, 0, '25테스트글입니다.', '25테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (26, 0, '26테스트글입니다.', '26테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (27, 0, '27테스트글입니다.', '27테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (28, 0, '28테스트글입니다.', '28테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (29, 0, '29테스트글입니다.', '29테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (30, 0, '30테스트글입니다.', '30테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (31, 0, '31테스트글입니다.', '31테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (32, 0, '32테스트글입니다.', '32테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (33, 0, '33테스트글입니다.', '33테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (34, 0, '34테스트글입니다.', '34테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (35, 0, '35테스트글입니다.', '35테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (36, 0, '36테스트글입니다.', '36테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (37, 0, '37테스트글입니다.', '37테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (38, 0, '38테스트글입니다.', '38테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (39, 0, '39테스트글입니다.', '39테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (40, 0, '40테스트글입니다.', '40테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (41, 0, '41테스트글입니다.', '41테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (42, 0, '42테스트글입니다.', '42테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (43, 0, '43테스트글입니다.', '43테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (44, 0, '44테스트글입니다.', '44테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (45, 0, '45테스트글입니다.', '45테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (46, 0, '46테스트글입니다.', '46테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (47, 0, '47테스트글입니다.', '47테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (48, 0, '48테스트글입니다.', '48테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (49, 0, '49테스트글입니다.', '49테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (50, 0, '50테스트글입니다.', '50테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (51, 0, '51테스트글입니다.', '51테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (52, 0, '52테스트글입니다.', '52테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (53, 0, '53테스트글입니다.', '53테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (54, 0, '54테스트글입니다.', '54테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (55, 0, '55테스트글입니다.', '55테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (56, 0, '56테스트글입니다.', '56테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (57, 0, '57테스트글입니다.', '57테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (58, 0, '58테스트글입니다.', '58테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (59, 0, '59테스트글입니다.', '59테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (60, 0, '60테스트글입니다.', '60테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (61, 0, '61테스트글입니다.', '61테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (62, 0, '62테스트글입니다.', '62테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (63, 0, '63테스트글입니다.', '63테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (64, 0, '64테스트글입니다.', '64테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (65, 0, '65테스트글입니다.', '65테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (66, 0, '66테스트글입니다.', '66테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (67, 0, '67테스트글입니다.', '67테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (68, 0, '68테스트글입니다.', '68테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (69, 0, '69테스트글입니다.', '69테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (70, 0, '70테스트글입니다.', '70테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (71, 0, '71테스트글입니다.', '71테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (72, 0, '72테스트글입니다.', '72테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (73, 0, '73테스트글입니다.', '73테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (74, 0, '74테스트글입니다.', '74테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (75, 0, '75테스트글입니다.', '75테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (76, 0, '76테스트글입니다.', '76테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (77, 0, '77테스트글입니다.', '77테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (78, 0, '78테스트글입니다.', '78테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (79, 0, '79테스트글입니다.', '79테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (80, 0, '80테스트글입니다.', '80테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (81, 0, '81테스트글입니다.', '81테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (82, 0, '82테스트글입니다.', '82테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (83, 0, '83테스트글입니다.', '83테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (84, 0, '84테스트글입니다.', '84테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (85, 0, '85테스트글입니다.', '85테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (86, 0, '86테스트글입니다.', '86테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (87, 0, '87테스트글입니다.', '87테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (88, 0, '88테스트글입니다.', '88테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (89, 0, '89테스트글입니다.', '89테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (90, 0, '90테스트글입니다.', '90테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (91, 0, '91테스트글입니다.', '91테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (92, 0, '92테스트글입니다.', '92테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (93, 0, '93테스트글입니다.', '93테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (94, 0, '94테스트글입니다.', '94테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (95, 0, '95테스트글입니다.', '95테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (96, 0, '96테스트글입니다.', '96테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (97, 0, '97테스트글입니다.', '97테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (98, 0, '98테스트글입니다.', '98테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (99, 0, '99테스트글입니다.', '99테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (100, 0, '100테스트글입니다.', '100테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (101, 0, '101테스트글입니다.', '101테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');
INSERT INTO t30_board (article_no, parent_no, title, content, image_file_name, write_date, id) VALUES (102, 0, '102테스트글입니다.', '102테스트글입니다.', null, TIMESTAMP '2023-04-03 12:00:00', 'mklinkj');


CREATE TABLE t30_attach_file
(
    uuid          VARCHAR2(100)           NOT NULL,
    file_name     VARCHAR2(250)           NOT NULL,
    file_type     CHAR(1) DEFAULT 'I'     NOT NULL,
    register_date DATE    DEFAULT SYSDATE NOT NULL,
    article_no    NUMBER(10)              NOT NULL,
    CONSTRAINT FK_t30_attach_file__t30_board FOREIGN KEY (article_no)
        REFERENCES t30_board (article_no) ON DELETE CASCADE
);

INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('bf0890ec-3a79-4f63-a560-63e84ed4dcad', 'file_name_1.png', 'I',
        TIMESTAMP '2023-07-01 00:00:00', 20);
INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('90d08496-6da0-41ab-b9c5-3917007024ba', 'file_name_2.png', 'I',
        TIMESTAMP '2023-07-01 00:00:00', 20);
INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('d76170e7-bed7-4c16-8831-6c06ec10b305', 'file_name_3.png', 'I',
        TIMESTAMP '2023-07-01 00:00:00', 20);



INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('08bd6332-e4c3-4d99-bf13-6ddaa5c3a201', 'file_name_4.png', 'I',
        TIMESTAMP '2023-07-02 00:00:00', 30);
INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('dab145b3-9cec-4e58-a9d2-8be341d16093', 'file_name_5.png', 'I',
        TIMESTAMP '2023-07-02 00:00:00', 30);
INSERT INTO t30_attach_file (uuid, file_name, file_type, register_date, article_no)
VALUES ('4f4fa02f-570a-46b9-9ed8-5218b568d97e', 'file_name_6.png', 'I',
        TIMESTAMP '2023-07-02 00:00:00', 30);
