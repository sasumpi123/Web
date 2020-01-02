DROP SEQUENCE MYSQL;
drop table myboard;

create sequence myseq;

-- 번호, 작성자, 제목, 내용, 작성일
create table myboard(
	MYNO NUMBER PRIMARY KEY,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYTITLE VARCHAR2(2000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL
);


INSERT INTO MYBOARD VALUES (myseq.nextval, '관리자', '글쓰기 테스트', '테스트 내용입니다.',SYSDATE);

select * from myboard;
