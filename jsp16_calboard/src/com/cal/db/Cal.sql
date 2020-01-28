DROP SEQUENCE CALBOARDSEQ;
drop table calboard;

create sequence calboardseq;
-- 번호, 아이디, 제목, 내용, 일정, 작성일
create table calboard(
	seq number primary key,
	id varchar2(1000) not null,
	title varchar2(2000) not null,
	content varchar2(4000) not null,
	mdate varchar2(12) not null,
	regdate date not null
);