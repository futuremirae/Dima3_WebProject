
/*

이 파일에 생성되어있는 테이블

- 공지사항(NOTICE)
- 문의사항(INQUIRY)
    - 댓글(REPLY)

*/


-- 공지사항 테이블 생성
-- 미인증 사용자도 확인 가능 -- 이제 못봄
-- 관리자만 작성 수정 삭제 가능
CREATE TABLE NOTICE
(
      NOTICE_NO                   NUMBER          PRIMARY KEY
    , NOTICE_WRITER               VARCHAR2(20)    REFERENCES 
                                                  USER_ACC(USER_ID) 
                                                  ON DELETE CASCADE
    , NOTICE_TITLE                VARCHAR2(300)   NOT NULL
    , NOTICE_CONTENT              VARCHAR2(3000)  NOT NULL
    , NOTICE_CREATE_DATE          DATE            DEFAULT SYSDATE
                                                  NOT NULL
    , NOTICE_UPDATE_DATE          DATE
    , NOTICE_ORIGINALFILENAME     VARCHAR2(300)
    , NOTICE_SAVEDFILENAME        VARCHAR2(300)
    , NOTICE_APPROVAL            CHAR(1)         DEFAULT 1
                                                  CHECK(board_APPROVAL IN
                                                  ('USER', 'ADMIN'))
                                                  NOT NULL
);




-- 문의사항 테이블 생성
-- 작성자와 로그인사용자가 같은 행만 출력
-- 관리자 = 모든 문의사항을 확인 가능
CREATE TABLE INQUIRY
(
      INQUIRY_NO                  NUMBER          PRIMARY KEY
    , INQUIRY_WRITER              VARCHAR2(20)    REFERENCES 
                                                  USER_ACC(USER_ID) 
                                                  ON DELETE CASCADE
    , INQUIRY_TITLE               VARCHAR2(300)   NOT NULL
    , INQUIRY_CONTENT             VARCHAR2(3000)  NOT NULL
    , INQUIRY_CREATE_DATE         DATE            DEFAULT SYSDATE
                                                  NOT NULL
    , INQUIRY_UPDATE_DATE         DATE
    , INQUIRY_ORIGINALFILENAME    VARCHAR2(300)
    , INQUIRY_SAVEDFILENAME       VARCHAR2(300)
    , INQUIRY_APPROVAL            CHAR(1)         DEFAULT 1
                                                  CHECK(board_APPROVAL IN
                                                  ('USER', 'ADMIN'))
                                                  NOT NULL
); -- 반드시 USER_ACC를 생성한 뒤 실행시킬 것


-- 문의 답변(댓글)테이블 생성
CREATE TABLE REPLY
(
    REPLY_NO            NUMBER          PRIMARY KEY
    , BOARD_NO          NUMBER          REFERENCES
                                        INQUIRY(INQUIRY_NO)
                                        ON DELETE CASCADE
    , REPLY_WRITER      VARCHAR2(20)    REFERENCES
                                        USER_ACC(USER_ID)
                                        ON DELETE CASCADE
    , REPLY_CONTENT     VARCHAR2(3000)  NOT NULL
    , REPLY_CREATE_DATE DATE            DEFAULT SYSDATE
                                        NOT NULL
    , REPLY_UPDATE_DATE DATE
); -- 반드시 BOARD를 생성한 뒤 실행시킬 것






-- 시퀀스 생성

CREATE SEQUENCE INQUIRY_SEQ;

CREATE SEQUENCE REPLY_SEQ;

-- 테이블 삭제
-- 삭제는 생성의 역순


DROP TABLE NOTICE;

DROP TABLE INQUIRY;

    DROP TABLE REPLY;


