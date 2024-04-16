
/*

�� ���Ͽ� �����Ǿ��ִ� ���̺�

- ��������(NOTICE)
- ���ǻ���(INQUIRY)
    - ���(REPLY)

*/


-- �������� ���̺� ����
-- ������ ����ڵ� Ȯ�� ���� -- ���� ����
-- �����ڸ� �ۼ� ���� ���� ����
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




-- ���ǻ��� ���̺� ����
-- �ۼ��ڿ� �α��λ���ڰ� ���� �ุ ���
-- ������ = ��� ���ǻ����� Ȯ�� ����
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
); -- �ݵ�� USER_ACC�� ������ �� �����ų ��


-- ���� �亯(���)���̺� ����
CREATE TABLE REPLY
(
    REPLY_NO            NUMBER          PRIMARY KEY
    , INQUIRY_NO          NUMBER          REFERENCES
                                        INQUIRY(INQUIRY_NO)
                                        ON DELETE CASCADE
    , REPLY_WRITER      VARCHAR2(20)    REFERENCES
                                        USER_ACC(USER_ID)
                                        ON DELETE CASCADE
    , REPLY_CONTENT     VARCHAR2(3000)  NOT NULL
    , REPLY_CREATE_DATE DATE            DEFAULT SYSDATE
                                        NOT NULL
    , REPLY_UPDATE_DATE DATE
); -- �ݵ�� BOARD�� ������ �� �����ų ��






-- ������ ����

CREATE SEQUENCE INQUIRY_SEQ;

CREATE SEQUENCE REPLY_SEQ;

-- ���̺� ����
-- ������ ������ ����


DROP TABLE NOTICE;

DROP TABLE INQUIRY;

    DROP TABLE REPLY;


