package com.dima.niceweb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
	@GetMapping("/join")
	public String join() {
		return"Join/join";
	}
	

}
