
/*
내 SQL선생님
https://gent.tistory.com/361

이 파일에 생성되어있는 테이블

- 카테고리 테이블 (CTGY)
    - 대분류 (MAIN_CTGY)
    - 소분류 (SUB_CTGY)
    - 상품명 (ITEM_NM)

- URL 테이블(CLIENT)
    - 회사 (CMP)
    - 국가 (NTN)
        - 국가코드변환 (NTN_TEXT)
    - 회사정보(INFO)
*/


-- 카테고리 테이블 생성

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


-- URL 테이블 생성 (부모) ( 단순 저장용, 조회는 이거로 안함 )
-- JOIN으로 받은 USER_ACC 객체를 저장해야하기 때문에 정규화되지 않은 원데이터 틀로 저장후 조회할 때는 정규화된 테이블을 이용
CREATE TABLE CLIENT
(
      DUNS_NO           VARCHAR2(100)   PRIMARY KEY     -- DUNS넘버
    , CMP_NM            VARCHAR2(100)   NOT NULL        -- 회사명
    , NAT_ID            VARCHAR2(1000)  NOT NULL        -- 국가 아이디
    , NAT_CD            CHAR(2)         NOT NULL        -- 국가 코드
    , NAT_KOR           VARCHAR2(20)    NOT NULL        -- 국가명 (한글)
    , NAT_ENG           VARCHAR2(20)    NOT NULL        -- 국가명 (영문)
    , CITY              VARCHAR2(100)   NOT NULL        -- 도시명
    , ADR               VARCHAR2(100)   NOT NULL        -- 전체 주소
    , SIC_CD            VARCHAR2(100)   NOT NULL        -- 주요 취급 품목
    , SALES             NUMBER(20)                      -- 매출
    , ASSET             NUMBER(20)                      -- 자산
    , EMP               NUMBER(10)                      -- 종업원수
    , CONTACT_GRD_CD    VARCHAR2(4)                     -- 마케팅 등급
    -- NOT NULL이 아닌 대신 NICE의 신용조사 미응시한 모든 사용자에게 다른 사용자에게 노출이 어렵다는 점을 경고창 생성
    -- , CREDIT_GRD_CD     VARCHAR2(4)                     -- 신용 등급
    , URL               VARCHAR2(200)   NOT NULL        -- 홈페이지 주소
    , EML               VARCHAR2(100)   NOT NULL        -- 이메일 주소
    , ENG               VARCHAR2(2000)  NOT NULL        -- 관련 키워드 목록
);

-- 회사 정보 VIEW 생성
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


-- 국가 VIEW 생성
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


-- 주소 테이블 생성
            CREATE VIEW LOC
                AS
                    SELECT  NTN_ID
                            , CITY
                            , ADR
                    FROM CLIENT
                    WITH READ ONLY
                ;
                
-- 국가 기본정보 테이블 생성
CREATE TABLE STAT_INFO
( 
      STAT_NO       NUMBER          PRIMARY KEY     -- 국가 넘버
    , STAT_NTN      VARCHAR2(50)    UNIQUE NOT NULL -- 국가 명
    , STAT_POP      NUMBER          NOT NULL        -- 국가 인구
    , STAT_CPT      VARCHAR2(50)    UNIQUE NOT NULL -- 국가 수도
    , STAT_LAN      VARCHAR2(50)    NOT NULL        -- 국가 언어
);


-- 국가 통계 테이블 생성
-- 5년치를 담을 예정

-- 이거 생각보다 졸라 복잡하게 담아야할듯
-- 수출액 수입액 무역수지 경제성장률 GDP 각각 따로 테이블 만들고 연도를 컬럼으로 만들어야 할 거 같다
-- 수출액 테이블
    CREATE TABLE STAT_EXP
    ( 
          STAT_NO       NUMBER      REFERENCES          -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
        , EXP_2019      NUMBER      NOT NULL            -- 수출액
        , EXP_2020      NUMBER      NOT NULL            -- 수입액
        , EXP_2021      NUMBER      NOT NULL            -- 무역수지
        , EXP_2022      NUMBER      NOT NULL            -- 경제성장률
        , EXP_2023      NUMBER      NOT NULL            -- 1인당 GDP
    );
    
-- 수입액 테이블
    CREATE TABLE STAT_IMP
    ( 
          STAT_NO       NUMBER      REFERENCES          -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
        , IMP_2019      NUMBER      NOT NULL            -- 수출액
        , IMP_2020      NUMBER      NOT NULL            -- 수입액
        , IMP_2021      NUMBER      NOT NULL            -- 무역수지
        , IMP_2022      NUMBER      NOT NULL            -- 경제성장률
        , IMP_2023      NUMBER      NOT NULL            -- 1인당 GDP
    );
    
-- 무역수지 테이블
    CREATE TABLE STAT_BAL
    ( 
          STAT_NO       NUMBER      REFERENCES          -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
        , BAL_2019      NUMBER      NOT NULL            -- 수출액
        , BAL_2020      NUMBER      NOT NULL            -- 수입액
        , BAL_2021      NUMBER      NOT NULL            -- 무역수지
        , BAL_2022      NUMBER      NOT NULL            -- 경제성장률
        , BAL_2023      NUMBER      NOT NULL            -- 1인당 GDP
    );
    
-- 경제성장률 테이블
    CREATE TABLE STAT_GWT
    ( 
          STAT_NO       NUMBER      REFERENCES          -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
        , GWT_2019      NUMBER      NOT NULL            -- 수출액
        , GWT_2020      NUMBER      NOT NULL            -- 수입액
        , GWT_2021      NUMBER      NOT NULL            -- 무역수지
        , GWT_2022      NUMBER      NOT NULL            -- 경제성장률
        , GWT_2023      NUMBER      NOT NULL            -- 1인당 GDP
    );
    
-- GDP 테이블
    CREATE TABLE STAT_GDP
    ( 
          STAT_NO       NUMBER      REFERENCES          -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
        , GDP_2019      NUMBER      NOT NULL            -- 수출액
        , GDP_2020      NUMBER      NOT NULL            -- 수입액
        , GDP_2021      NUMBER      NOT NULL            -- 무역수지
        , GDP_2022      NUMBER      NOT NULL            -- 경제성장률
        , GDP_2023      NUMBER      NOT NULL            -- 1인당 GDP
    );  


-- 국가 규제사항 테이블 생성

CREATE TABLE STAT_REG
( 
      STAT_NO       NUMBER          REFERENCES      -- 국가 넘버
                                    STAT_INFO(STAT_NO) 
                                    ON DELETE CASCADE
    , STAT_REG1     VARCHAR2(500)    NOT NULL        -- 규제품목
    , STAT_REG2     VARCHAR2(500)    NOT NULL        -- 규제분류
    , STAT_REG3     VARCHAR2(500)    NOT NULL        -- 규제대상국
    , STAT_HS       VARCHAR2(500)    NOT NULL        -- 규제대상 HS CODE
);




-- 테이블 삭제
-- 삭제는 생성의 역순

DROP TABLE CTGY;

    DROP VIEW MAIN_CTGY;
    
    DROP VIEW SUB_CTGY;
    
    DROP VIEW ITEM;



DROP TABLE CLIENT;

    DROP VIEW CMP;
    
    DROP VIEW NTN;