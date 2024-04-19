package com.dima.niceweb.myfavcompany;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavCmpService { // 마이페이지  - 찜기능 관현 서비스 부분 입니다.
	
	private final FavCmpRepository favCmpRepository; // 저장소에서 불러온 데이터 입니다. 

}
