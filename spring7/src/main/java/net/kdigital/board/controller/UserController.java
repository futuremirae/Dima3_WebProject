package net.kdigital.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.UserDTO;
import net.kdigital.board.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	/**
	 * 회원가입을 위한 화면 요청 
	 * @return
	 */
	@GetMapping("/user/join") // 회원 가입 화면 요청 
	public String join() {
		return"user/join";
	}
	
	@PostMapping("/user/joinProc")
	public String joinProc(@ModelAttribute UserDTO userDTO) {
		log.info("로그를 던지라"+userDTO.toString());
		log.info("로그를 던지라 {}", userDTO.toString());
		// 추가작업 
		
		// 롤, 계정 활성화 여부 세팅 
		userDTO.setRoles("ROLE_USER");//일반 사용자임 
		userDTO.setEnabled(true);
		userService.joinProc(userDTO);
		return "redirect:/";
		
	}
	
	
	// 로그인 화면 요청 --> 주의) 로그인 처리는 Controller에 넣지 않는다 
	@GetMapping("/user/login")
	public String login(// 첫화면에서 넘어올때는 꼭 필요한 값이 아님 
			@RequestParam(value="error", required = false) String error
			,@RequestParam(value="errMessage", required = false) String errMessage,
			Model model) {
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);
		return "user/login";
	}
	
	@GetMapping("/user/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name="userId") String userId) {
		//userRepository.existsById(userDTO.getUserId());
		
		
		boolean flag = userService.findByUserId(userId);
		
		return flag; // 데이터가 없다면 true -회원가입 가능 ! 
		
	} 

}
