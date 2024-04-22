package net.kdigital.market.controller;



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
import net.kdigital.market.dto.CommentDTO;
import net.kdigital.market.service.CommentService;

@Controller
@Slf4j
@RequiredArgsConstructor // 서비스에 넘길거 - 하위에 final 지정 꼭 해줘야 
@RequestMapping("/reply")
public class CommentController {
	private final CommentService commentService; 
	
	
	/**
	 * 댓글 삽입 
	 * @param commentDTO
	 * @return
	 */
	@PostMapping("/replyInsert")
	@ResponseBody
	public CommentDTO ReplyInsert(@ModelAttribute CommentDTO commentDTO) { // 넘어올때 sendData있음 
		
		log.info("{}", commentDTO);
		CommentDTO saveResult = commentService.replyInsert(commentDTO);
		if(saveResult != null) {// 댓글 등록이 완료되고 
			
		}
		return saveResult; 
	}
	
	/**
	 * 댓글 모두 반환 
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/replyAll")
	@ResponseBody
	public List<CommentDTO> replyAll(@RequestParam(name="boardNum") Long boardNum){
		 List<CommentDTO> replyList = commentService.replyAll(boardNum);
		 
		 log.info(replyList.toString());
		 return replyList;
		
	}
	
	/**
	 * 댓글 삭제 
	 * @param replyNum
	 * @return
	 */
	@GetMapping("/replydelete")
	@ResponseBody
	public String replydelete(@RequestParam(name="replyNum") Long replyNum) {
		
		
		
		commentService.replyDelete(replyNum);
		
		return "success";
	}
	
	
	/**
	 * 댓글 수정 
	 * @param replyNum
	 * @return
	 */
	@GetMapping("/replyCorrect")
	@ResponseBody
	public CommentDTO replyCorrect(@RequestParam(name="replyNum") Long replyNum) {
		//log.info("get으로 오고있다 "+ replyNum);
		CommentDTO replyDTO = commentService.select(replyNum);
		return replyDTO;
		
	}
	
	@PostMapping("/replyCorrect")
	@ResponseBody
	public String replyCorrect(@RequestParam(name="replyNum") Long replyNum,
								@RequestParam(name="replyText") String replyText) {
		//log.info("post으로 오고있다 "+replyNum+replyText);
		commentService.update(replyNum,replyText);
		return "success";
		
	}
	

}
