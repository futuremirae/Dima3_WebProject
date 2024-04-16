package com.dima.niceweb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="USER_ACC") // 실제 테이블과 이름이 같으면생략 가능 
public class UserEntity {
	@SequenceGenerator(
			name="userSeq"
			,sequenceName="USER_SEQ" // 실제 디비 이름 똑같이 
			,initialValue = 1
			,allocationSize = 1
			)
	@Id
	@GeneratedValue(generator = "userSeq")
	@Column(name="USER_NUM" , nullable = false) // 시퀀스 -> PK
	private String UserNum;
	
	@Column(name="USER_ID" , nullable = false) // 사용자 아이디 
	private String userId;
	
	@Column(name="USER_PWD" , nullable = false) //사용자 패스워드
	private String userPwd;
	
	@Column(name="USER_DUNS_NO" , nullable = false) // 던스넘버 
	private String userDunsNo;
	
	@Column(name="USER_CMP_ENG" , nullable = false) // 영문 회사
	private String userCmpEng;
	
	@Column(name="USER_CMP_KOR") // 한글 회사명 
	private String userCmpKor;
	
	@Column(name="USER_NTN" , nullable = false) // 국가 
	private String userNtn;
	
	@Column(name="USER_CEO_ENG" , nullable = false) // 대표명 영문 
	private String userCeoEng;
	
	@Column(name="USER_CEO_KOR") // 대표명 한글 
	private String userCeoKor;
	
	@Column(name="USER_EML" , nullable = false) // 담당자 이메일 
	private String userEmail;
	
	@Column(name="USER_PUB_EML" , nullable = false) // 회사 대표 이메일 
	private String userPubEmail;
	
	@Column(name="USER_SIC_CD" , nullable = false) // 주요 취급품목 
	private String userSicCd;
	
	@Column(name="USER_URL" , nullable = false) // 회사 url
	private String userUrl;
	
	@Column(name="USER_ADR" , nullable = false) // 담당자 주소 
	private String userAdr;
	
	@Column(name="USER_NAME" , nullable = false) // 담당자명 
	private String userName;
	
	@Column(name="USER_PHONE" , nullable = false) // 담당자 연락처 
	private String userPhone;
	
	@Column(name="USER_KEYWORD") // 사용자 관심 키워드 
	private String userKeyWord;
	
	@Column(name="USER_ROLE") //권한 
	private String userRoles;
	
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.UserNum(userDTO.getUserNum())
				.userId(userDTO.getUserId())
				.userPwd(userDTO.getUserPwd())
				.userDunsNo(userDTO.getUserDunsNo())
				.userCmpEng(userDTO.getUserCmpEng())
				.userCmpKor(userDTO.getUserCmpKor())
				.userNtn(userDTO.getUserNtn())
				.userCeoEng(userDTO.getUserCeoEng())
				.userCeoKor(userDTO.getUserCeoKor())
				.userEmail(userDTO.getUserEmail())
				.userPubEmail(userDTO.getUserEmail())
				.userSicCd(userDTO.getUserSicCd())
				.userUrl(userDTO.getUserUrl())
				.userAdr(userDTO.getUserAdr())
				.userName(userDTO.getUserName())
				.userPhone(userDTO.getUserPhone())
				.userKeyWord(userDTO.getUserKeyWord())
				.userRoles(userDTO.getUserRoles())
				.build();
	}
	
	

}
