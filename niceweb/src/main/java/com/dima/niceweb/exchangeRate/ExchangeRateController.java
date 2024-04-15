package com.dima.niceweb.exchangeRate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateController {
	

	@GetMapping("/rate")
	public String rate() {
		//Map<String, Double> result =rateService.restTest();
		//log.info("결과값 출력: "+ result);
		return "Main/exchangeRate";
	}

}
