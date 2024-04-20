package com.dima.niceweb.myfavcompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dima.niceweb.user.UserEntity;
import com.dima.niceweb.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavCmpService { // 마이페이지  - 찜기능 관현 서비스 부분
	
	private final FavCmpRepository favCmpRepository; //  찜리스트 
	private final UserRepository userRepository;
	
	/**
	 * 사용자의 찜한 회사 리스트를 모두 반환하는 서비스 함수 
	 * @param userNum
	 * @return
	 */
	public List<FavCmpDTO> favCmpAll(Long userNum) {
		
		List<FavCmpEntity> entity = favCmpRepository.findAll();
		
		log.info("서비스 부분입니다만!!{}번입니다",userNum);
		UserEntity userEntity =userRepository.findById(userNum).get(); // 사용자 엔티티찾고 

		
		List<FavCmpEntity> favCmpEntityList = favCmpRepository.findAllByUserEntity(userEntity);
		
		List<FavCmpDTO> favCmpDTOList = new ArrayList<>();
		
		// entity를 dto로 변환하는 작업 
		for(FavCmpEntity temp : favCmpEntityList) {
			
			FavCmpDTO dto =FavCmpDTO.toDTO(temp, userNum);
			favCmpDTOList.add(dto);
		}
		
		return favCmpDTOList;
	}
	
	/**
	 * 찜 리스트에서 삭제 
	 * @param favCmpNo
	 */
	public void favCmpDelete(Long favCmpNo) {
		favCmpRepository.deleteById(favCmpNo);
		
		
	}

}
