package net.kdigital.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.ReplyDTO;
import net.kdigital.board.service.ReplyService;

@Controller
@Slf4j
@RequiredArgsConstructor // 서비스에 넘길거 - 하위에 final 지정 꼭 해줘야 
@RequestMapping("/reply")
public class ReplyController {
	
	private final ReplyService replyService; 
	@PostMapping("/replyInsert")
	@ResponseBody
	public ReplyDTO ReplyInsert(@ModelAttribute ReplyDTO replyDTO) { // 넘어올때 sendData있음 
		
		log.info("{}", replyDTO);
		ReplyDTO saveResult = replyService.replyInsert(replyDTO);
		if(saveResult != null) {// 댓글 등록이 완료되고 
			
		}
		return saveResult; 
	}
	
	@GetMapping("/replyAll")
	@ResponseBody
	public List<ReplyDTO> replyAll(@RequestParam(name="boardNum") Long boardNum){
		 List<ReplyDTO> replyList = replyService.replyAll(boardNum);
		
		 return replyList;
		
	}
	
	@GetMapping("/replydelete")
	@ResponseBody
	public String replydelete(@RequestParam(name="replyNum") Long replyNum) {
		
		
		
		replyService.replyDelete(replyNum);
		
		return "success";
	}
	
	@GetMapping("/replycount")
	@ResponseBody
	public int replycount(@RequestParam(name="boardNum") int boardNum) {
		
		// 보드 엔티찾고 
		
		List<ReplyDTO> replyList = replyService.replyAll((long) boardNum);
		return replyList.size();
	}
	
	@GetMapping("/replyCorrect")
	@ResponseBody
	public ReplyDTO replyCorrect(@RequestParam(name="replyNum") Long replyNum) {
		log.info("get으로 오고있다 "+ replyNum);
		ReplyDTO replyDTO = replyService.select(replyNum);
		return replyDTO;
		
	}
	
	@PostMapping("/replyCorrect")
	@ResponseBody
	public String replyCorrect(@RequestParam(name="replyNum") Long replyNum,
								@RequestParam(name="replyText") String replyText) {
		log.info("post으로 오고있다 "+replyNum+replyText);
		replyService.update(replyNum,replyText);
		return "success";
		
	}

}
