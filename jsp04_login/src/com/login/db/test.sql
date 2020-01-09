DROP SEQUENCE MYMEMBERSEQ;
DROP TABLE MYMEMBER;


CREATE SEQUENCE MYMEMBERSEQ;
--멤버 번호, 아이디, 패스워드, 이름, 주소, 전화번호, 이메일, 가입여부, 등급
-- 가입여부(Y-가입,N-탈퇴), 등급
CREATE TABLE MYMEMBER(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(500) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	CONSTRAINT MYMEMBER_PK PRIMARY KEY(MYNO),
	CONSTRAINT MYMEMBER_UQ_ID UNIQUE(MYID),
	CONSTRAINT MYMEMBER_UQ_PHONE UNIQUE(MYPHONE),
	CONSTRAINT MYMEMBER_UQ_EMAIL UNIQUE(MYEMAIL),
	CONSTRAINT MYMEMBER_CHK_ENABLED CHECK(MYENABLED IN ('Y','N'))
);

INSERT INTO MYMEMBER VALUES (MYMEMBERSEQ.NEXTVAL, 'admin', '1234', '관리자', '경기도 용인시 기흥구', '010-1234-1234', 'SASUMPI123@NAVER.COM', 'Y', 'ADMIN');
INSERT INTO MYMEMBER VALUES (MYMEMBERSEQ.NEXTVAL, '1234', '1234', '회원', '경기도 용인시 기흥구', '010-1234-1235', 'SASUMPI1235@NAVER.COM', 'Y', 'asdf');

SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MYMEMBER;

UPDATE MYMEMBER SET MYROLE = 'ADMIN' WHERE MYNO = 1;
