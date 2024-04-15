package net.kdigital.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.Iris;
import net.kdigital.board.service.PredictService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PredictController {
	
	private final PredictService service; 
	
	/**
	 * 붓꽃 정보를 예축하기 위해 화면요청 
	 * @return
	 */
	@GetMapping("/predict")
	public String predict() {
		return "iris";
	}
	@PostMapping("/predict")
	@ResponseBody
	public Map<String,String> predict(@ModelAttribute Iris iris) {
		service.restTest();
		//Map<String,String> result =  service.predictRest(iris);
		//log.info("헤이헤니!!!!"+result.toString());
		return null;
	}


}
