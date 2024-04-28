package com.dima.niceweb.exchangeRate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateController {
	

	@GetMapping("/rate")
	public String rate() {
		
		return "Main/banking";
	}

}
