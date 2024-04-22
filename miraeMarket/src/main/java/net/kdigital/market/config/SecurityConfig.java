 package net.kdigital.market.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.handler.CustomFailureHandler;


@Configuration  // SecurityConfigrk 설정 클래스임을 나타내는 Annotation
@EnableWebSecurity //웹 보안은 모두 이클래스의 설정에 따름을 나타내는Annotation 웹에 관련 보안설정이 모두 여기서 이루어진다 라는 것을 나타냄 
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomFailureHandler failureHandler;
	
	@Bean  //외부에서 접근해서, 우리가 객체를 생성해서 쓸일이 없으므로 public은 지워도된다 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// 앱 요청에 대한 접급권한 설정  http라는 객체를 접근 권한을 설정한다 
		http
			.authorizeHttpRequests((auth)->auth// 인증 절차에 대해서 설정 
					.requestMatchers(// 로그인을 안해도 되는 것 
							"/"
							,"/board/boardList"
//							,"/board/boardDetail"
//							,"/board/boardSearch"
							,"/board/search"
							,"/user/join" // 화면 요청 
							,"/user/confirmId" 
							,"/user/joinProc" // 데이터 베이스에 저장 해달라는 요청 - post 
							,"/user/login"
							,"/reply/replyAll"
							,"/images/**"
							,"/css/**"
							,"/script/**").permitAll()//permitAll()은 인증 절차 없이도 접근가능한 요청 
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
					.anyRequest().authenticated() // 위에는 권한에 따라 분리를 하였고, 기타 다른 경로눈 인증된 사용자만 접근 가능, 가장 마지막에 둘것 
					);
		// Custom Login 설정 : 내가 만든 로그인 화면 
		http
			.formLogin((auth)->auth
					.loginPage("/user/login") // login 화면 요청 - 내가만듬 login.html 페이지를 보여줘야 함으로 - 컨트롤러에서 따로 생성  
					.failureHandler(failureHandler)// 로그인 실패시 처리할 핸들러 등록 . 에러가났을때 핸들링 해주는 객체를 등록 한다 
					.usernameParameter("userId") //sucurity에서는 username이 id
					.passwordParameter("userPwd")
					.loginProcessingUrl("/user/loginProc") // 로그인 처리 
					.defaultSuccessUrl("/").permitAll()// 로그인이 되면 첫화면으로 간다 
			
					) ;
		// 로그아웃 설정
		http
			.logout((auth)->auth
					.logoutUrl("/user/logout") // 로그 아웃 처리 url 
					.logoutSuccessUrl("/") // 로그아웃 성공시 이동 url 
					.invalidateHttpSession(true) // 세션을 무효화 
					.deleteCookies("JSESSIONID") // 로그아웃 성공시 제거할 쿠키명   사용자의 로그 기록에 대한 정보를 쿠키 정보라고 함 
					);
		// CSRF(Cross - Site Request Forery) 비활성화  개발을 편리하게 해주기 위해 
		// 시큐리티는 사이트 위변조 기능이 설정되기 때문에 모든 POST 요청시 
		// CRSF 토큰도 보내한다 
		// 개발하는 동안에는 disabled 해준다 (POST, PUT, DELETE이 진행 되지않음) -- 로그아웃은 get 방식이어도 , post 방식으로 처리함으 나중에 로그아웃이 안된다 
		//POST, PUT-update를 put이라고 부름 , DELETE ==> db에 접근하는 동작들이다 
		http 
			.csrf((auth) -> auth.disable());// 배포시 다시 활성화 
			
					return http.build();
	} 
	
	// 비밀번호 암호화 , 단방향 암호화 
	@Bean // Spring 컨테이너에 의해 관리되는 빈을 생성하는 메서드
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

