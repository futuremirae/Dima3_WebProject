package net.kdigital.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import net.kdigital.market.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private String roles;// 롤 (사용자, 관리자 계정인지)
	private Boolean enabled; //(활성호 여부 )
	

	public static UserDTO toDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.userId(userEntity.getUserId())
				.userName(userEntity.getUserName())
				.userPwd(userEntity.getUserPwd())
				.phone(userEntity.getPhone())
				.roles(userEntity.getRoles())
				.enabled(userEntity.getEnabled())
				.build();
				
				
	}
	

}

