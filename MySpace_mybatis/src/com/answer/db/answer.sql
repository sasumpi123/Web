DROP SEQUENCE BOARDSEQ;
DROP SEQUENCE REPLYSEQ;
DROP TABLE BOARD;

CREATE SEQUENCE BOARDSEQ;
CREATE SEQUENCE REPLYSEQ;

CREATE TABLE BOARD(
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(500) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL,
	REGROUP NUMBER NOT NULL,
	RESTEP NUMBER NOT NULL,
	RETAB NUMBER NOT NULL,
	DELFLAG VARCHAR2(500) NOT NULL,
	CONSTRAINT BOARD_DEL_CHK CHECK (DELFLAG IN ('Y', 'N'))
);

SELECT * FROM BOARD ORDER BY REGROUP DESC, RESTEP;

INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '관리자', 'test', 'test 내용입니다', SYSDATE, REPLYSEQ.NEXTVAL, 1, 0, 'N' );

INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '관리자', '1번글', '1번글 내용입니다', SYSDATE, REPLYSEQ.NEXTVAL, 1, 0, 'N' );

INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '관리자', '2번글', '2번글 내용입니다', SYSDATE, REPLYSEQ.NEXTVAL, 1, 0, 'N' );

UPDATE BOARD SET RESTEP = RESTEP + 1 WHERE REGROUP = (SELECT REGROUP FROM BOARD WHERE SEQ = 1) AND RESTEP > (SELECT RESTEP FROM BOARD WHERE SEQ = 1);
INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '학생1', 'RE:1번글의 답글', 'RE:1번글의 답글 내용', SYSDATE, (SELECT REGROUP FROM BOARD WHERE SEQ = 1), (SELECT RESTEP FROM BOARD WHERE SEQ = 1) + 1, (SELECT RETAB FROM BOARD WHERE SEQ = 1) + 1, 'N');

UPDATE BOARD SET RESTEP = RESTEP + 1 WHERE REGROUP = (SELECT REGROUP FROM BOARD WHERE SEQ = 2) AND RESTEP > (SELECT RESTEP FROM BOARD WHERE SEQ = 2);
INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '학생2', 'RE:2번글의 답글', 'RE:2번글의 답글 내용', SYSDATE, (SELECT REGROUP FROM BOARD WHERE SEQ = 2), (SELECT RESTEP FROM BOARD WHERE SEQ = 2) + 1, (SELECT RETAB FROM BOARD WHERE SEQ = 2) + 1, 'N');

UPDATE BOARD SET RESTEP = RESTEP + 1 WHERE REGROUP = (SELECT REGROUP FROM BOARD WHERE SEQ = 3) AND RESTEP > (SELECT RESTEP FROM BOARD WHERE SEQ = 3);
INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '학생3', 'RE:RE:1번글의 답글의 답글','RE:RE:1번글의 답글의 답글 내용', SYSDATE, (SELECT REGROUP FROM BOARD WHERE SEQ = 3), (SELECT RESTEP+1 FROM BOARD WHERE SEQ = 3), (SELECT RETAB+1 FROM BOARD WHERE SEQ = 3), 'N');

UPDATE BOARD SET RESTEP = RESTEP +1 WHERE REGROUP = (SELECT REGROUP FROM BOARD WHERE SEQ = 4) AND RESTEP > (SELECT RESTEP FROM BOARD WHERE SEQ = 4);
INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '학생4', 'RE:RE:2번글의 답글의 답글','RE:RE:2번글의 답글의 답글 내용', SYSDATE, (SELECT REGROUP FROM BOARD WHERE SEQ = 4), (SELECT RESTEP+1 FROM BOARD WHERE SEQ = 4), (SELECT RETAB+1 FROM BOARD WHERE SEQ = 4), 'N' )




