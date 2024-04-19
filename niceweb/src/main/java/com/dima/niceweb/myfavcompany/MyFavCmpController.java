package com.dima.niceweb.myfavcompany;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor 
public class MyFavCmpController {// 마이페이지 - 찜기능 관련 컨트롤러
	private final FavCmpService favCmpService; // 서비스 불러오기 
	
	@GetMapping("/myFavCompany")
	public String favCmp() {
		return "myPage/favCompany";
	}

}
