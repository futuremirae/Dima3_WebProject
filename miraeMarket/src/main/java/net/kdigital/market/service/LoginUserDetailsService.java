package net.kdigital.market.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.LoginUserDetails;
import net.kdigital.market.dto.UserDTO;
import net.kdigital.market.entity.UserEntity;
import net.kdigital.market.repository.UserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	
	private final  UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		
		
		log.info("{}",userId);
		
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(()->{
				throw new UsernameNotFoundException("error 발생");
				});          // 아이디 못찾으면 강제적으로 에러를 발생 
		
		UserDTO userDTO = UserDTO.toDTO(userEntity);
		
		// 반환을 UserDetails로 반환해야하므로 UserDTO를 UserDetails로 바꿔야함 ! 
		return (UserDetails) new LoginUserDetails(userDTO);
	}

}
