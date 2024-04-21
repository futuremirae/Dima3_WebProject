package com.dima.niceweb.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
	private final EmailService mailService;

	@PostMapping("/mailsend")
	public String sendMail(EmailDTO mailDTO) {
		
		
		log.info("########"+mailDTO.toString()); // 데이터가 확인 
		mailService.mailSend(mailDTO);
		mailService.insertMail(mailDTO);// 보낸 메일함에 메시지 저
		return "redirect:/";

	}
}
