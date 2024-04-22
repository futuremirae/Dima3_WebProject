package net.kdigital.market.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.dto.UserDTO;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="market_member")
public class UserEntity {
	
	@Id
	@Column(name="member_id")
	private String userId;
	
	@Column(name="member_name" , nullable = false)
	private String userName;
	
	@Column(name="member_pw" , nullable = false)
	private String userPwd;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(name="rolename")
	private String roles;
	
	private Boolean enabled;
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.userId(userDTO.getUserId())
				.userName(userDTO.getUserName())
				.userPwd(userDTO.getUserPwd())
				.phone(userDTO.getPhone())
				.roles(userDTO.getRoles())
				.enabled(userDTO.getEnabled())
				.build();
				
				
	}
	
	

}
