drop sequence boardnoseq;
drop sequence groupnoseq;
drop table answerboard;
commit

select * from user_sequences;

create sequence boardnoseq;   -- 글번호
create sequence groupnoseq;   -- 그룹 번호

select boardnoseq.nextval from dual;
select groupnoseq.nextval from dual;

create table answerboard(
	boardno number primary key,		-- 글 번호
	groupno number not null,		-- 그룹 번호
	groupseq number not null,		-- 그룹 순서
	titletab number not null,		-- 제목 탭
	title varchar2(500) not null,	-- 제목
	content varchar2(4000) not null,-- 내용
	writer varchar2(100) not null,	-- 작성자
	regdate date not null			-- 작성 날짜
);

select * from answerboard order by boardno desc, groupseq;

-- 글번호, 그룹번호, 그룹순서, 제목탭, 제목, 작성자
-- 3, 3, 1, 0, qclass 파이팅, 반장
-- 2, 2, 1, 0, 이거살아있나?, 이동헌
-- 6, 2, 2(부모+1), 1(부모+1), ㄴRE:ㄴ 죽은듯, asdf
-- 7, 2, 3(부모+1), 2(부모+1)
-- 4, 2, 2(부모+1), 1(부모+1), ㄴRE:ㄴ 죽은듯 , 무리무리
-- 5, 2, 3(부모+1), 2(부모+1), ㄴRE: ㅇ ,반장

-- 1, 1, 1, 0, test글, 관리자

-- 첫번째 글 작성
insert into answerboard
values(boardnoseq.nextval, groupnoseq.nextval,1,0,'3번글 입니다','3번글 내용입니다','관리자',sysdate);

-- 두번째 글 작성
insert into answerboard
values(boardnoseq.nextval, groupnoseq.nextval,1,0,'2번글입니다','2번글 내용입니다','관리자',SYSDATE);

-- 1번글입니다(글번호 1번)에 답변 달기
-- update : 부모의 글과 같은 그룹번호, 부모의 그룹순서보다 더 큰 그룹순서
-- 그룹 순서를 + 1
update answerboard
set groupseq = groupseq+1
where groupno = (select groupno from answerboard where boardno = 1)
and groupseq > (select groupseq from answerboard where boardno = 1);


insert into answerboard
values(
boardnoseq.nextval,
(select groupno from answerboard where boardno = 1)
(select groupseq from answerboard where boardno = 1)+1,
(select titletab from answerboard where boardno =1)+1,
'RE:1번글 입니다',
'1번글에 답변달기',
'학생',
sysdate
);

update answerboard
set groupseq = groupseq+1
where groupno = (select groupno from answerboard where boardno = 3)
and groupseq > (select groupseq from answerboard where boardno = 3)


 



