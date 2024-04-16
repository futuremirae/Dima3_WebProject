-- ĥ�ǿ� ������� sql ���̺� ������

/*

�� ���Ͽ� �����Ǿ��ִ� ���̺�

- ���� ���̺� (USER_ACC)
    - ���̾� ���ã�� (FAVORITE)
    - ���� ������ (SENDED_EMLAIL)
    - �ֱ� �˻��� (SEARCH_LOG)

*/


-- [ ���� ���̺� ] ���� 
CREATE TABLE USER_ACC
(
      USER_NUM      NUMBER          CONSTRAINT USER_SEQ PRIMARY KEY
    , USER_ID       VARCHAR2(20)    UNIQUE NOT NULL             -- ����� ���̵�
    , USER_PWD      VARCHAR2(100)   NOT NULL                    -- ����� ��й�ȣ
    , USER_DUNS_NO  VARCHAR2(9)     NOT NULL                    -- ����� ȸ�� ���� ��ȣ
    , USER_CMP_ENG  VARCHAR2(100)   NOT NULL                    -- ����� ȸ���(����)
    , USER_CMP_KOR  VARCHAR2(100)                               -- ����� ȸ���(�ѱ�)
    , USER_NTN      CHAR(2)                                     -- ����� ���� �ڵ�
    , USER_CEO_ENG  VARCHAR2(50)    NOT NULL                    -- ����� ��ǥ�ڸ�(����)
    , USER_CEO_KOR  VARCHAR2(50)                                -- ����� ��ǥ�ڸ�(�ѱ�)
    , USER_EML      VARCHAR2(100)   NOT NULL                    -- ����� �̸��� (�����)
    , USER_PUB_EML  VARCHAR2(100)   NOT NULL                    -- ����� �̸��� (ȸ�� ����)
    , USER_SIC_CD   VARCHAR2(100)   NOT NULL                    -- ����� ��� ǰ��
    , USER_URL      VARCHAR2(200)   NOT NULL                    -- ����� ȸ�� URL
    , USER_NTN      VARCHAR2(50)    NOT NULL                    -- ����� ���� (����)
    , USER_ADR      VARCHAR2(200)   NOT NULL                    -- ����� �ּ� (����)
    , USER_NAME     VARCHAR2(30)    NOT NULL                    -- ����� �̸�(�����)
    , USER_PHONE    VARCHAR2(30)    NOT NULL                    -- ����� ��ȭ��ȣ(�����)
--    , USER_BUSINESS VARCHAR2(200)   CHECK (USER_BUSINESS IN   -- ����� ����
--                                    ('IMPORTER', 'EXPORTER', 'MANUFACTURER', 'DISTRIBUTOR')) 
--                                    NOT NULL    -- ������ �츮�� �����ߴ� �Ͱ� �޶� CHECK OPTION�� �����ؾ���
    , USER_KEYWORD  VARCHAR2(500)                               -- ����� ���� Ű����
    , USER_ROLE     VARCHAR2(20)    DEFAULT 'ROLE_USER'         -- ����� ���� ����
                                    CHECK (USER_ROLE IN
                                    ('ROLE_USER','ROLE_ADMIN'))
                                    NOT NULL
    -- ���� (SALES)
    -- �ڻ� (ASSET)
    -- �������� (EMPLOYEES)
    -- �ֿ� ��� ǰ�� (SIC_CD) (����)
    -- ���̾��� / ������ ��� < �̰� D&B�� DB���� �޾ƾ���
    -- �������� ��� ����
    -- ���� ����ȭ ���� -- ����(24.04.12)
);

-- user_id�� fk�� �޴� ���� [ ������ ���̺� ] ����
-- �ۼ��ڿ� �α��λ���ڰ� ���� �ุ ���
CREATE TABLE SENDED_EMAIL
(
      USER_NUM          NUMBER          REFERENCES              -- ����� ���̵� = ���� ���
                                        USER_ACC(USER_NUM)
                                        ON DELETE CASCADE
    , RECEIVER          VARCHAR2(50)    NOT NULL                -- ������ �޴� ���
    , REPLY_CONTENT     VARCHAR2(3000)  NOT NULL                -- ���� ����
    --, REPLY_FILE
    , SENDED_DATE       DATE            NOT NULL
    -- ÷������ ��¼��
);


-- ���̾� ���� ���̺� ����
-- ���̾� ���ýÿ� ��ǥ�� ������ ���ʿ� ����ǰԲ�
-- �׸��� USER_ACC ���̺�� ID���� �����Ͽ� ���� ������ �α��ΰ����� ������ ��쿡�� ���
CREATE TABLE FAVORITE
(   
      USER_NUM           NUMBER         REFERENCES
                                        USER_ACC(USER_NUM)
                                        ON DELETE CASCADE
    , RECEIVER          VARCHAR2(50)    NOT NULL
    , REPLY_CONTENT     VARCHAR2(3000)  NOT NULL
    , SENDED_DATE       DATE
    -- ��Ÿ ��� ���
);

-- ����ں� �ֱ� �˻�� ������ ���̺� ����
-- prepend�� ����Ͽ� ���� �ֱٿ� �˻��� �˻�� ���� �տ� ����ǰԲ� ����
CREATE TABLE SEARCH_LOG
(
      USER_NUM           NUMBER         REFERENCES
                                        USER_ACC(USER_NUM)
                                        ON DELETE CASCADE
    , USER_LOG          VARCHAR2(50)                        -- ����� �˻� ����
);

-- ������ �ȴٸ�
-- �ӽ� ������ << ���� �ļ���


-- ������ ����
CREATE SEQUENCE USER_ACC_SEQ;


-- ���̺� ����


DROP TABLE USER_ACC;

    DROP TABLE SENDED_EMAIL;
    
    DROP TABLE FAVORITE;
    
    DROP TABLE SEARCH_LOG;
