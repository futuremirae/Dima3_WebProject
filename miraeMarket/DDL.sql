--1)회원 테이블 만들기 
DROP TABLE market_member;
CREATE TABLE market_member
 (
 	member_id VARCHAR2(20)  PRIMARY KEY
    , member_pw VARCHAR2(100)  NOT NULL
    , member_name VARCHAR2(20) NOT NULL -- 패스워드는 암호화 되서 저장 되므로 기본 60자임 
    , phone VARCHAR2(20) NOT NULL
    ,enabled CHAR(1) DEFAULT '1' CHECK(enabled IN ('1','0')) -- 'ROLE_USER', 'ROLE_ADMIN' 등 이 있음 
    ,rolename VARCHAR2(20) DEFAULT 'ROLE_USER' -- 사용자가 활성화된 상태인 
 
 );


-- 2)게시판 테이블 만들기 
DROP TABLE market_board; 

CREATE TABLE market_board(
    board_num  NUMBER  constraint board_num PRIMARY KEY
    ,member_id VARCHAR2(20) constraint member_id NOT NULL
    ,title   VARCHAR2(200) constraint title NOT NULL
    ,contents VARCHAR2(2000) constraint contents NOT NULL
    ,input_date date default sysdate
    ,category VARCHAR2(50)
    ,soldout CHAR(1) default 'N'
    ,buyer_id VARCHAR2(20)
  
);

DROP SEQUENCE market_board_seq;
CREATE SEQUENCE market_board_seq;  -- 시퀀스 생성 

select * from market_board;

-- 3) 댓글 
DROP TABLE market_comment;
DROP SEQUENCE market_reply_seq; 
CREATE SEQUENCE market_reply_seq;-- 시퀀스 생성 
CREATE TABLE market_comment
 (
 	content_num NUMBER PRIMARY KEY
    , board_num NUMBER REFERENCES market_board(board_num) ON DELETE CASCADE 
    , member_id VARCHAR2(20) NOT NULL -- 패스워드는 암호화 되서 저장 되므로 기본 60자임 
    ,comment_text VARCHAR2(500) 
    , input_date DATE DEFAULT sysdate
   
 );
 
select * from market_comment;


