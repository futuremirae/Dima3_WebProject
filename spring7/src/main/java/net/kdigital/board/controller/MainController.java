package net.kdigital.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.LoginUserDetails;

@Controller
public class MainController {
	@GetMapping({ "", "/" })
	public String index() {

		// if(loginUser != null) {
		// model.addAttribute("loginId", loginUser.getUseName());
		//
		// }
		return "index";
	}

}
