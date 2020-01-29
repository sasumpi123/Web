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

INSERT INTO CALBOARD VALUES (CALBOARDSEQ.NEXTVAL, 'kh', 'ㅎㅇ', 'ㅎㅇ', '20200129', SYSDATE);
select * from calboard;
delete from calboard where mdate = 20200129;


-- row_number() over(partition by @ order by)
-- @ group by 해서, 그룹 별 rownum 사용할 때

select *
from(
	select (row_number() over(partition by substr(mdate,1,8)order by mdate))rn,
	seq, id, title, content, mdate, regdate
	from calboard
	where id = 'kh' and substr(mdate,1,6)='202001'
)
where rn between 1 and 3;