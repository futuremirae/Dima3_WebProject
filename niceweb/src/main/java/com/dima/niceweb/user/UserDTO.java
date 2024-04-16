package com.dima.niceweb.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
	private String UserNum; // 시퀀스 -> PK
	private String userId; // 사용자 아이디 
	private String userPwd; //사용자 패스워드
	private String userDunsNo;// 던스넘버 
	private String userCmpEng; // 영문 회사명 
	private String userCmpKor; // 한글 회사명 
	private String userNtn;// 국가 
	private String userCeoEng;// 대표명 영문 
	private String userCeoKor; // 대표명 한글 
	private String userEmail;// 담당자 이메일 
	private String userPubEmail;// 회사 대표 이메일 
	private String userSicCd;// 주요 취급품목 
	private String userUrl;// 회사 url
	private String userAdr;// 담당자 주소 
	private String userName;// 담당자명 
	private String userPhone;// 담당자 연락처 
	private String userKeyWord; // 사용자 관심 키워드 
	private String userRoles;//권한 
	
	public static UserDTO toDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.UserNum(userEntity.getUserNum())
				.userId(userEntity.getUserId())
				.userPwd(userEntity.getUserPwd())
				.userDunsNo(userEntity.getUserDunsNo())
				.userCmpEng(userEntity.getUserCmpEng())
				.userCmpKor(userEntity.getUserCmpKor())
				.userNtn(userEntity.getUserNtn())
				.userCeoEng(userEntity.getUserCeoEng())
				.userCeoKor(userEntity.getUserCeoKor())
				.userEmail(userEntity.getUserEmail())
				.userPubEmail(userEntity.getUserEmail())
				.userSicCd(userEntity.getUserSicCd())
				.userUrl(userEntity.getUserUrl())
				.userAdr(userEntity.getUserAdr())
				.userName(userEntity.getUserName())
				.userPhone(userEntity.getUserPhone())
				.userKeyWord(userEntity.getUserKeyWord())
				.userRoles(userEntity.getUserRoles())
				.build();
	}
	
	
	
	

	

}

//USER_NUM      NUMBER          CONSTRAINT USER_SEQ PRIMARY KEY
//, USER_ID       VARCHAR2(20)    UNIQUE NOT NULL             -- 사용자 아이디
//, USER_PWD      VARCHAR2(100)   NOT NULL                    -- 사용자 비밀번호
//, USER_DUNS_NO  VARCHAR2(9)     NOT NULL                    -- 사용자 회사 던스 번호
//, USER_CMP_ENG  VARCHAR2(100)   NOT NULL                    -- 사용자 회사명(영문)
//, USER_CMP_KOR  VARCHAR2(100)                               -- 사용자 회사명(한글)
//, USER_NTN      CHAR(2)         NOT NULL                    -- 사용자 국가 코드
//, USER_CEO_ENG  VARCHAR2(50)    NOT NULL                    -- 사용자 대표자명(영문)
//, USER_CEO_KOR  VARCHAR2(50)                                -- 사용자 대표자명(한글)
//, USER_EML      VARCHAR2(100)   NOT NULL                    -- 사용자 이메일 (담당자)
//, USER_PUB_EML  VARCHAR2(100)   NOT NULL                    -- 사용자 이메일 (회사 공용)
//, USER_SIC_CD   VARCHAR2(100)   NOT NULL                    -- 사용자 취급 품목
//, USER_URL      VARCHAR2(200)   NOT NULL                    -- 사용자 회사 URL
//, USER_ADR      VARCHAR2(200)   NOT NULL                    -- 사용자 주소 (영문)
//, USER_NAME     VARCHAR2(30)    NOT NULL                    -- 사용자 이름(담당자)
//, USER_PHONE    VARCHAR2(30)    NOT NULL                    -- 사용자 전화번호(담당자)
//--    , USER_BUSINESS VARCHAR2(200)   CHECK (USER_BUSINESS IN   -- 사용자 업종
//--                                    ('IMPORTER', 'EXPORTER', 'MANUFACTURER', 'DISTRIBUTOR')) 
//--                                    NOT NULL    -- 업종도 우리가 생각했던 것과 달라서 CHECK OPTION도 수정해야함    
//, USER_KEYWORD  VARCHAR2(500)                               -- 사용자 관심 키워드
//, USER_ROLE     VARCHAR2(20)    DEFAULT 'ROLE_USER'         -- 사용자 가입 구분
//                                CHECK (USER_ROLE IN
//                                ('ROLE_USER','ROLE_ADMIN'))
//                                NOT NULL
