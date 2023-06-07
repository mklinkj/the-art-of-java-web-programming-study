DROP TABLE IF EXISTS t_member CASCADE;

CREATE TABLE t_member
(
    id        VARCHAR(10) PRIMARY KEY,
    pwd       VARCHAR(200) NOT NULL,
    name      VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL,
    join_date DATE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

INSERT INTO t_member (id, pwd, name, email, join_date)
VALUES ('mklinkj', '{bcrypt}$2a$10$x.jjGAH0ejiHcWnOtT.fh.7DIST36OLKLHucPkaSrh4/aczSZpAbu', '정션링크', 'mklinkj@github.com', '2023-02-01');

INSERT INTO t_member (id, pwd, name, email, join_date)
VALUES ('hong', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '홍길동', 'hong@gamil.com', '2023-02-02');

INSERT INTO t_member (id, pwd, name, email, join_date)
VALUES ('lee', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '이순신', 'lee@test.com', '2023-02-03');

INSERT INTO t_member (id, pwd, name, email, join_date)
VALUES ('kim', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '김유신', 'hong@gamil.com', '2023-02-04');

INSERT INTO t_member (id, pwd, name, email, join_date)
VALUES ('choi', '{bcrypt}$2a$10$gjrfhS0Hht9nD6IFaP1.EuZi7wUROvgsw0UxUvQw66uRPk1svxMEm', '최치원', 'choi@gamil.com', '2023-02-04');
