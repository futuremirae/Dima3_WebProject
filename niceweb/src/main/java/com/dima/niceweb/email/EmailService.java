package com.dima.niceweb.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dima.niceweb.user.UserEntity;
import com.dima.niceweb.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
	private final JavaMailSender mailSender;
	private final UserRepository userRepository;
	private final EmailRepository emailRepository;
	

// @Autowired //(생성자 자동 주입방식을 사용할 경우 쓰면 에러가 난다, 같이 사용 불가)
//	public EmailService(JavaMailSender mailSender) { // 구글 메일센더 
//		this.mailSender = mailSender;
//	}

	@Value("${spring.mail.username}") // 보내는 사람 계정은 관리자 계정임을 주의한다!
	String username;

	/**
	 * 메일 발송
	 * @param mailDTO
	 */
	public void mailSend(EmailDTO mailDTO) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDTO.getReceiver());
		message.setFrom(username);
		message.setSubject(mailDTO.getEmailSubject());
		message.setText(mailDTO.getEmailContent());
		mailSender.send(message);

	}
	
	/**
	 * 보낸 메시지 저장 
	 * @param mailDTO
	 */
	public void insertMail(EmailDTO mailDTO) {
		
		// mailDTO의 사용자 고유 넘버를 통해 사용자ENTITY를 찾아온다. 
		UserEntity userEntity = userRepository.findById(mailDTO.getUserNum()).get();
		
		EmailEntity emailEntity = EmailEntity.toEntity(mailDTO, userEntity);
		emailRepository.save(emailEntity);
	
		
	}
}
