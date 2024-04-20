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
	
	/*
	 * 찜 리스트에 추가하기 
	 */
	public Boolean favCmpInsert(Long userNum, String dunsNo) {
		
		// 찜한 리스트에 추가하려고 하는 회사가 있는지에 대해서 찾기 
		
		// 1) 우선은 사용자가 favorite의 테이블에 존재하는지 검사하기 
		
		
		UserEntity userEntity =userRepository.findById(userNum).get(); // 사용자 엔티티를 먼저 찾는다. 
		List<FavCmpEntity> favCmpEntityList = favCmpRepository.findAllByUserEntity(userEntity);
		// 사용자가 존재하지 않는다면 
		if(favCmpEntityList.isEmpty()) {
			// insert를 해주면 된다 
			
		}else {
			log.info("사용자가 존재해용!!!");
		}
		
		
		return null;
	}

}
