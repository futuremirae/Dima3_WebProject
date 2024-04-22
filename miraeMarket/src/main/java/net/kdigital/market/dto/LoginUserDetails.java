package net.kdigital.market.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

@ToString
public class LoginUserDetails implements UserDetails {
	
	
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private String roles;// 롤 (사용자, 관리자 계정인지)
	private Boolean enabled; //(활성호 여부 )
	
	

	public LoginUserDetails(UserDTO userDTO) {
		super();
		this.userId = userDTO.getUserId();
		this.userPwd = userDTO.getUserPwd();
		this.userName = userDTO.getUserName();
		this.phone = userDTO.getPhone();
		this.roles = userDTO.getRoles();
		this.enabled = userDTO.getEnabled();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>(); // 롤이 여러개인 사이트를 개발할때 
		//GrantedAuthority는 인터페이스므로 new을 할수 가 없다라는 것 - 밑에 추상 메소드를추가 해야한다는 것 
		collection.add(new GrantedAuthority() {

			// 객체를 저장할때는 직렬화한다 - 외부의 역질렬화와 같다라는 것을 증명하기 위해서 시리얼 넘버를 붙여줘야한다 
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	
	// 이름 반환 - 사용자 정의 메소드 
	public String getUseName() { // 시큐리티 에서는 userid를 getUsername이라고 한다 
		// TODO Auto-generated method stub
		return this.userName;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
