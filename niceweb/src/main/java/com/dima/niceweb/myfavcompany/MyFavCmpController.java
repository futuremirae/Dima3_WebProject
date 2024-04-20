package com.dima.niceweb.myfavcompany;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor 
public class MyFavCmpController {// 마이페이지 - 찜기능 관련 컨트롤러
	private final FavCmpService favCmpService; // 서비스 불러오기 
	
	/**
	 * 마이페이지의 찜기능 페이지 반환 
	 * @return
	 */
	@GetMapping("/myFavCompany")
	public String favCmp() {
		return "myPage/favCompany";
	}
	
	/**
	 * 찜한 회사목록 모두 출력 
	 * @return
	 */
	@GetMapping("/favCmpAll")
	@ResponseBody
	public List<FavCmpDTO> favCmpAll(@RequestParam(name = "userNum") Long userNum) {
		log.info("유저 넘버은??{}", userNum);
		List<FavCmpDTO> favCmpList  =favCmpService.favCmpAll(userNum);
		log.info("반환값은 무엇일까??????{}", favCmpList);
		return favCmpList;
	}
	
	@GetMapping("/favCmpDelete")
	@ResponseBody
	public String favCmpDelete(@RequestParam(name="favCmpNo") Long favCmpNo) {
		log.info("넘어온 번호{}",favCmpNo);
		favCmpService.favCmpDelete(favCmpNo);
		
		
		return"success";
	}
	
	@GetMapping("/favCmpInsert")
	@ResponseBody
	public String favCmpInsert(@RequestParam(name="dunsNo") String dunsNo,@RequestParam(name="userNum") Long userNum ) {
		log.info("넘어온 거 출력해보기~~~~"+userNum+"~~~"+dunsNo);
		
		Boolean result = favCmpService.favCmpInsert(userNum,dunsNo);
		
		
		
		
		return"success!";
	}
	
	
	

}
