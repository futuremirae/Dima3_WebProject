--2024년 3월 12일 

--회원 전용 게시판 테이블 

-- 객체 삭제 명령 


DROP TABLE reply;
DROP TABLE board;
DROP TABLE members;

DROP SEQUENCE reply;
DROP SEQUENCE board;
DROP SEQUENCE members;

--1) 회원 테이블 

CREATE TABLE reply;

CREATE TABLE members;



--2) 게시판 테이블 
CREATE TABLE board(
    board_num  NUMBER  constraint board_seq PRIMARY KEY
    ,board_writer VARCHAR2(20) constraint board_writer NOT NULL
    ,board_title   VARCHAR2(200) default '제목없음'
    ,board_content VARCHAR2(4000)
    ,hit_count NUMBER default 0
    ,favorite_count NUMBER default 0
    ,create_date DATE default sysdate
    ,update_date DATE
  
);

-- 첨부파일 
CREATE SEQUENCE board_seq;

ALTER TABLE board ADD originalFileName VARCHAR2(200);
ALTER TABLE board ADD savedFileName VARCHAR2(200);

--3) 댓글 테이블 

DROP TABLE reply;
DROP SEQUENCE reply_seq;

CREATE TABLE reply(

    reply_num NUMBER PRIMARY KEY
    , board_num NUMBER REFERENCES board(board_num) ON DELETE CASCADE -- 게시글 번
    ,reply_writer VARCHAR2(20) --댓글 작성자 
    ,reply_text VARCHAR2(1000) NOT NULL -- 댓글내용 
    ,create_date DATE DEFAULT sysdate -- 댓글 작성자 
    
);
CREATE SEQUENCE reply_seq; 

--4) 회원 가입 
DROP TABLE boarduser;
 CREATE TABLE boarduser 
 (
 	user_id VARCHAR2(50) PRIMARY KEY
    , user_name VARCHAR2(50) NOT NULL
    , user_pwd VARCHAR2(100) NOT NULL -- 패스워드는 암호화 되서 저장 되므로 기본 60자임 
    , email VARCHAR2(40) NOT NULL
    ,roles VARCHAR2(20) DEFAULT 'ROLE_USER' -- 'ROLE_USER', 'ROLE_ADMIN' 등 이 있음 
    ,enabled CHAR(1) DEFAULT '1' CHECK(enabled IN ('1','0')) -- 사용자가 활성화된 상태인 
 
 );

