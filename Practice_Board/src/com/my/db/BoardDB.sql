select * from myboard;
CREATE TABLE MYBOARD(
MYNO NUMBER PRIMARY KEY,
MYNAME VARCHAR2(1000) NOT NULL,
	MYTITLE VARCHAR2(2000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL)

INSERT INTO MYBOARD VALUES (boardseq.NEXTVAL,'asdf','asdf','asdf',SYSDATE);
SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD ORDER BY MYNO DESC;

create sequence boardseq
