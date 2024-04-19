package com.dima.niceweb.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/mail")
	public String sendMailpage() {
		//mailSer
//		log.info("하이룽가 에불ㄹ하");
//		log.info(mailDTO.toString());
	//	mailService.mailSend(mailDTO);
		return "Email/email";
	
	
	

}
	@PostMapping("/mailsend")
	public String sendMail(EmailDTO mailDTO) {
		//mailSer
		log.info("하이룽가 에불ㄹ하");
		log.info(mailDTO.toString());
		mailService.mailSend(mailDTO);
		return "Email/email";
	
	
	

}
}
