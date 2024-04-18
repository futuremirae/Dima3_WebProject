
/*
<<<<<<< HEAD
ë‚´ SQLì„ ìƒë‹˜
https://gent.tistory.com/361

ì´ íŒŒì¼ì— ìƒì„±ë˜ì–´ìžˆëŠ” í…Œì´ë¸”

- ì¹´í…Œê³ ë¦¬ í…Œì´ë¸” (CTGY)
    - ëŒ€ë¶„ë¥˜ (MAIN_CTGY)
    - ì†Œë¶„ë¥˜ (SUB_CTGY)
    - ìƒí’ˆëª… (ITEM_NM)

- URL í…Œì´ë¸”(CLIENT)
    - íšŒì‚¬ (CMP)
    - êµ­ê°€ (NTN)
        - êµ­ê°€ì½”ë“œë³€í™˜ (NTN_TEXT)
    - íšŒì‚¬ì •ë³´(INFO)
*/


-- ì¹´í…Œê³ ë¦¬ í…Œì´ë¸” ìƒì„±
=======
³» SQL¼±»ý´Ô
https://gent.tistory.com/361

ÀÌ ÆÄÀÏ¿¡ »ý¼ºµÇ¾îÀÖ´Â Å×ÀÌºí

- Ä«Å×°í¸® Å×ÀÌºí (CTGY)
    - ´ëºÐ·ù (MAIN_CTGY)
    - ¼ÒºÐ·ù (SUB_CTGY)
    - »óÇ°¸í (ITEM_NM)

- URL Å×ÀÌºí(CLIENT)
    - È¸»ç (CMP)
    - ±¹°¡ (NTN)
        - ±¹°¡ÄÚµåº¯È¯ (NTN_TEXT)
    - È¸»çÁ¤º¸(INFO)
*/


-- Ä«Å×°í¸® Å×ÀÌºí »ý¼º
>>>>>>> origin/feature-jin

CREATE TABLE CTGY
(
      MAIN_NM   VARCHAR2(50)
    , SUB_NM    VARCHAR2(50)
    , ITEM_NM   VARCHAR2(50)
    , MAIN_ID   VARCHAR2(50)
    , SUB_ID    VARCHAR2(50)
    , ITEM_ID   VARCHAR2(50)
);
    
            CREATE VIEW MAIN_CTGY
                AS
                    SELECT  MAIN_ID
                            , MAIN_NM
                    FROM CTGY
                    WITH READ ONLY
                ;
            
            
            CREATE VIEW SUB_CTGY
                AS
                    SELECT  SUB_ID
                            , SUB_NM
                            , MAIN_ID
                    FROM CTGY
                    WITH READ ONLY
                ;
            
            
            CREATE VIEW ITEM
                AS
                    SELECT  ITEM_ID
                            , ITEM_NM
                            , SUB_ID
                    FROM CTGY
                    WITH READ ONLY
                ;


<<<<<<< HEAD
-- URL í…Œì´ë¸” ìƒì„± (ë¶€ëª¨) ( ë‹¨ìˆœ ì €ìž¥ìš©, ì¡°íšŒëŠ” ì´ê±°ë¡œ ì•ˆí•¨ )
-- JOINìœ¼ë¡œ ë°›ì€ USER_ACC ê°ì²´ë¥¼ ì €ìž¥í•´ì•¼í•˜ê¸° ë•Œë¬¸ì— ì •ê·œí™”ë˜ì§€ ì•Šì€ ì›ë°ì´í„° í‹€ë¡œ ì €ìž¥í›„ ì¡°íšŒí•  ë•ŒëŠ” ì •ê·œí™”ëœ í…Œì´ë¸”ì„ ì´ìš©
CREATE TABLE CLIENT
(
      DUNS_NO           VARCHAR2(100)   PRIMARY KEY     -- DUNSë„˜ë²„
    , CMP_NM            VARCHAR2(100)   NOT NULL        -- íšŒì‚¬ëª…
    , NAT_ID            VARCHAR2(1000)  NOT NULL        -- êµ­ê°€ ì•„ì´ë””
    , NAT_CD            CHAR(2)         NOT NULL        -- êµ­ê°€ ì½”ë“œ
    , NAT_KOR           VARCHAR2(20)    NOT NULL        -- êµ­ê°€ëª… (í•œê¸€)
    , NAT_ENG           VARCHAR2(20)    NOT NULL        -- êµ­ê°€ëª… (ì˜ë¬¸)
    , CITY              VARCHAR2(100)   NOT NULL        -- ë„ì‹œëª…
    , ADR               VARCHAR2(100)   NOT NULL        -- ì „ì²´ ì£¼ì†Œ
    , SIC_CD            VARCHAR2(100)   NOT NULL        -- ì£¼ìš” ì·¨ê¸‰ í’ˆëª©
    , SALES             NUMBER(20)                      -- ë§¤ì¶œ
    , ASSET             NUMBER(20)                      -- ìžì‚°
    , EMP               NUMBER(10)                      -- ì¢…ì—…ì›ìˆ˜
    , CONTACT_GRD_CD    VARCHAR2(4)                     -- ë§ˆì¼€íŒ… ë“±ê¸‰
    -- NOT NULLì´ ì•„ë‹Œ ëŒ€ì‹  NICEì˜ ì‹ ìš©ì¡°ì‚¬ ë¯¸ì‘ì‹œí•œ ëª¨ë“  ì‚¬ìš©ìžì—ê²Œ ë‹¤ë¥¸ ì‚¬ìš©ìžì—ê²Œ ë…¸ì¶œì´ ì–´ë µë‹¤ëŠ” ì ì„ ê²½ê³ ì°½ ìƒì„±
    , CREDIT_GRD_CD     VARCHAR2(4)                     -- ì‹ ìš© ë“±ê¸‰
    , URL               VARCHAR2(200)   NOT NULL        -- í™ˆíŽ˜ì´ì§€ ì£¼ì†Œ
    , EML               VARCHAR2(100)   NOT NULL        -- ì´ë©”ì¼ ì£¼ì†Œ
    , ENG               VARCHAR2(2000)  NOT NULL        -- ê´€ë ¨ í‚¤ì›Œë“œ ëª©ë¡
);

-- íšŒì‚¬ ì •ë³´ VIEW ìƒì„±
=======
-- URL Å×ÀÌºí »ý¼º (ºÎ¸ð) ( ´Ü¼ø ÀúÀå¿ë, Á¶È¸´Â ÀÌ°Å·Î ¾ÈÇÔ )
-- JOINÀ¸·Î ¹ÞÀº USER_ACC °´Ã¼¸¦ ÀúÀåÇØ¾ßÇÏ±â ¶§¹®¿¡ Á¤±ÔÈ­µÇÁö ¾ÊÀº ¿øµ¥ÀÌÅÍ Æ²·Î ÀúÀåÈÄ Á¶È¸ÇÒ ¶§´Â Á¤±ÔÈ­µÈ Å×ÀÌºíÀ» ÀÌ¿ë
CREATE TABLE CLIENT
(
      DUNS_NO           VARCHAR2(100)   PRIMARY KEY     -- DUNS³Ñ¹ö
    , CMP_NM            VARCHAR2(100)   NOT NULL        -- È¸»ç¸í
    , NAT_ID            VARCHAR2(1000)  NOT NULL        -- ±¹°¡ ¾ÆÀÌµð
    , NAT_CD            CHAR(2)         NOT NULL        -- ±¹°¡ ÄÚµå
    , NAT_KOR           VARCHAR2(20)    NOT NULL        -- ±¹°¡¸í (ÇÑ±Û)
    , NAT_ENG           VARCHAR2(20)    NOT NULL        -- ±¹°¡¸í (¿µ¹®)
    , CITY              VARCHAR2(100)   NOT NULL        -- µµ½Ã¸í
    , ADR               VARCHAR2(100)   NOT NULL        -- ÀüÃ¼ ÁÖ¼Ò
    , SIC_CD            VARCHAR2(100)   NOT NULL        -- ÁÖ¿ä Ãë±Þ Ç°¸ñ
    , SALES             NUMBER(20)                      -- ¸ÅÃâ
    , ASSET             NUMBER(20)                      -- ÀÚ»ê
    , EMP               NUMBER(10)                      -- Á¾¾÷¿ø¼ö
    , CONTACT_GRD_CD    VARCHAR2(4)                     -- ¸¶ÄÉÆÃ µî±Þ
    -- NOT NULLÀÌ ¾Æ´Ñ ´ë½Å NICEÀÇ ½Å¿ëÁ¶»ç ¹ÌÀÀ½ÃÇÑ ¸ðµç »ç¿ëÀÚ¿¡°Ô ´Ù¸¥ »ç¿ëÀÚ¿¡°Ô ³ëÃâÀÌ ¾î·Æ´Ù´Â Á¡À» °æ°íÃ¢ »ý¼º
    , CREDIT_GRD_CD     VARCHAR2(4)                     -- ½Å¿ë µî±Þ
    , URL               VARCHAR2(200)   NOT NULL        -- È¨ÆäÀÌÁö ÁÖ¼Ò
    , EML               VARCHAR2(100)   NOT NULL        -- ÀÌ¸ÞÀÏ ÁÖ¼Ò
    , ENG               VARCHAR2(2000)  NOT NULL        -- °ü·Ã Å°¿öµå ¸ñ·Ï
);

-- È¸»ç Á¤º¸ VIEW »ý¼º
>>>>>>> origin/feature-jin
            CREATE VIEW CMP
                AS
                    SELECT  DUNS_NO
                            , CMP_NM
                            , SIC_CD
                            , SALES
                            , ASSET
                            , EMP
                            , CONTACT_GRD_CD
                            , CREDIT_GRD_CD
                            , URL
                            , EML
                            , ENG
                            , NAT_ID
                    FROM CLIENT
                    WITH READ ONLY
                ;


<<<<<<< HEAD
-- êµ­ê°€ VIEW ìƒì„±
=======
-- ±¹°¡ VIEW »ý¼º
>>>>>>> origin/feature-jin
            CREATE VIEW NTN
                AS
                    SELECT  NAT_ID
                            , NAT_CD
                            , NAT_KOR
                            , NAT_ENG
                    FROM CLIENT
                    ORDER BY NAT_ID
                    WITH READ ONLY
                ;


<<<<<<< HEAD
-- ì£¼ì†Œ í…Œì´ë¸” ìƒì„±
=======
-- ÁÖ¼Ò Å×ÀÌºí »ý¼º
>>>>>>> origin/feature-jin
            CREATE VIEW LOC
                AS
                    SELECT  NTN_ID
                            , CITY
                            , ADR
                    FROM CLIENT
                    WITH READ ONLY
                ;


<<<<<<< HEAD
-- í…Œì´ë¸” ì‚­ì œ
-- ì‚­ì œëŠ” ìƒì„±ì˜ ì—­ìˆœ
=======
-- Å×ÀÌºí »èÁ¦
-- »èÁ¦´Â »ý¼ºÀÇ ¿ª¼ø
>>>>>>> origin/feature-jin

DROP TABLE CTGY;

    DROP VIEW MAIN_CTGY;
    
    DROP VIEW SUB_CTGY;
    
    DROP VIEW ITEM;



DROP TABLE CLIENT;

    DROP VIEW CMP;
    
    DROP VIEW NTN;