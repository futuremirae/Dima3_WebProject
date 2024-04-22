package net.kdigital.market.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.UserDTO;
import net.kdigital.market.entity.UserEntity;
import net.kdigital.market.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean joinProc(UserDTO userDTO) {
		boolean isExistUser = userRepository.existsById(userDTO.getUserId());
		if(isExistUser) return false; // 기존에 존재하는 아이디이므로 가입 실패 
		
		//가입이 가능하다면 비밀번호를 암호화 시킨다 
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
		
		
		//dto를 Entity 로 변경 
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);// 데이터 베이스에 저장 
		return true; 
		
		
	}

	public boolean findByUserId(String userId) {
		
		UserEntity entity = userRepository.findByUserId(userId);
		// 데이터가 들어있다면 
		if(entity != null) {
			return false;
		}
		return true;
		
	}

}
