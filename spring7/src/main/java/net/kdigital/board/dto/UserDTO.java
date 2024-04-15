package net.kdigital.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
	
	private String userId;
	private String userName;
	private String userPwd;
	private String email;
	private String roles;// 롤 (사용자, 관리자 계정인지)
	private Boolean enabled; //(활성호 여부 )
	
	//Entity --> DTO
	
	public static UserDTO toDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.userId(userEntity.getUserId())
				.userName(userEntity.getUserName())
				.userPwd(userEntity.getUserPwd())
				.email(userEntity.getEmail())
				.roles(userEntity.getRoles())
				.enabled(userEntity.getEnabled())
				.build();
				
				
	}
	

}

//user_id VARCHAR2(50) PRIMARY KEY
//, user_name VARCHAR2(50) NOT NULL
//, user_pwd VARCHAR2(100) NOT NULL -- 패스워드는 암호화 되서 저장 되므로 기본 60자임 
//, email VARCHAR2(40) NOT NULL
//,roles VARCHAR2(20) DEFAULT 'ROLE_USER' -- 'ROLE_USER', 'ROLE_ADMIN' 등 이 있음 
//,enabled CHAR(1) DEFAULT '1' CHECK(enabled IN ('1','0')) -- 사용자가 활성화된 상태인 