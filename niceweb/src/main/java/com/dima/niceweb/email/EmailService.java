package com.dima.niceweb.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
	private JavaMailSender mailSender;
	// private static final String From_ADDRESS= "mirae1296@gmail.com";

	// public MailService(JavaMailSender mailSender) {
	// this.mailSender = mailSender;
	// }

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Value("${spring.mail.username}")
	String username;

	public void mailSend(EmailDTO mailDTO) {
		log.info("하ㅓㅓㅜㄹㄴ알눌노ㅓㅏ~~~~~~~~~~" + username);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDTO.getAddress());
		message.setFrom(username);
		message.setSubject(mailDTO.getTitle());
		message.setText(mailDTO.getMessage());
		log.info("###########" + message.toString());
		mailSender.send(message);

	}
}
