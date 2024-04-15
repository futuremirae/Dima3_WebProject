package net.kdigital.board.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.Iris;

@Service
@RequiredArgsConstructor
@Slf4j
public class PredictService {
	@Value("${cosine.predict.server}")
	String url;
	
	
	private final RestTemplate restTemplate;
	
	public void restTest() {
		Map<String, Map<String, Double>> res = restTemplate.getForObject("https://open.er-api.com/v6/latest", Map.class);
		System.out.println(res.get("rates").get("KRW"));  // Dollor 대비 원화 환율
		}

	
	public Map<String, String> predictRest(Iris iris) {
		Map<String, String> error = new HashMap<>();
		Map<String, String> result =  new HashMap<>();
		log.info("url출력 "+url);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			log.info("1: {}", restTemplate);
			ResponseEntity<Map> response=restTemplate.postForEntity(url, iris, Map.class); // 맵을 보내면 결과를 iris로 받겠다 
			
			log.info("2: {}", restTemplate);
			result = response.getBody();
			
			
		}catch(Exception e){
			error.put("statusCode", "450");
			error.put("body", "오류났음");
			return error;
			
		}
		
		//RestTemplate
		return result;
	}

}
