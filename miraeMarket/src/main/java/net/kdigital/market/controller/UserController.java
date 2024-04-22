package net.kdigital.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.UserDTO;
import net.kdigital.market.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	@GetMapping("/user/join") // 회원 가입 화면 요청 
	public String join() {
		return"user/join";
	}
	
	
	/**
	 * 회원 가입 프로세스 
	 * @param userId
	 * @param userPwd
	 * @param userName
	 * @param phone
	 * @return
	 */
	@PostMapping("/user/joinProc") //"userPwd": userPwd, "userName": userName, "phone
	@ResponseBody
	public String joinProc(@RequestParam(name="userId")String userId,@RequestParam(name="userPwd") String userPwd,
			@RequestParam(name="userName") String userName,@RequestParam(name="phone") String phone) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setUserPwd(userPwd);
		userDTO.setUserName(userName);
		userDTO.setPhone(phone);
		userDTO.setRoles("ROLE_USER");//일반 사용자임 
		userDTO.setEnabled(true);
		userService.joinProc(userDTO);
		return "success";
		
	}
	
	/**
	 * 회원 가입 화면 요청 
	 * @param error
	 * @param errMessage
	 * @param model
	 * @return
	 */
	@GetMapping("/user/login") // 회원 가입 화면 요청 
	public String login(@RequestParam(value="error", required = false) String error
			,@RequestParam(value="errMessage", required = false) String errMessage,
			Model model) {
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);
		return"user/login";
	}
	
	/**
	 * 아이디 확인 데이터 베이스 있는지 확인하는 과정 
	 * @param userId
	 * @return
	 */
	@GetMapping("/user/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name="userId") String userId) {
		//userRepository.existsById(userDTO.getUserId());
		
		
		boolean flag = userService.findByUserId(userId);
//		if(flag==true) log.info("만들수 있도!!");
//		else log.info("폴스라서 못함!!");
		
		return flag; // 데이터가 없다면 true 만들수 있다는 의미 
		
	} 
	
}
