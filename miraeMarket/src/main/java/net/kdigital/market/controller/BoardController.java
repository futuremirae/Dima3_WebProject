package net.kdigital.market.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.service.BoardService;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService; 

	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
	
		log.info(boardDTO.toString());
		boardDTO.setSoldOut("N");
		
		boardService.insertproduct(boardDTO);
		
		return "redirect:/board/boardList";
	}
	
	// 상품 업데이트-- 구매하기 버튼 눌렀을때 
	@PostMapping("/boardUpdate")
	@ResponseBody
	@Transactional
	public String boardUpdate(@RequestParam(name="loginId") String loginId,
			@RequestParam(name="boardNum") Long boardNum) {
		
		log.info("아이디: {} 보드넘: {}",loginId, boardNum );
		boardService.buyOne(boardNum,loginId);

		
		
		return "성공입니다용!";
	}
	
	
	
	// 상품 목록 출력 
	@GetMapping("/boardList")
	public String boardList(Model model) { 
		
		
		List<BoardDTO> dtoList = boardService.selectAll();
		log.info(dtoList.toString());
		model.addAttribute("list",dtoList);
	
		return "board/boardList";
	}
	// 상품 목록 출력  -- 상품 검색 페이지에 init으로 넘길거임 
	@GetMapping("/boardSearchAll")
	@ResponseBody
	public List<BoardDTO> boardSearchAll(Model model) { 
		
		
		List<BoardDTO> dtoList = boardService.selectAll();
		log.info(dtoList.toString());
		model.addAttribute("list",dtoList);
	
		return dtoList;
	}
	
	
	
	
	
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardNum") Long boardNum,Model model,HttpServletRequest request) {
		
		
		BoardDTO boardDTO =boardService.selectOne(boardNum);//selectOne는 컨트롤러에서 하기 
	
		model.addAttribute("board",boardDTO);
		String contextPath =request.getContextPath();
		//log.info("보드 디테일 겟이다!"+boardDTO.toString());
		model.addAttribute("contextPath", contextPath);
		
		return "board/boardDetail";
		
	}
	
	/**
	 * 상품 삭제 
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardNum") Long boardNum
			) {
		
		//BoardDTO boardDTO = boardService.selectOne(boardNum);
		boardService.deleteOne(boardNum);
		
		
		return "redirect:/board/boardList";
		
	}
	

	/**
	 * 상품 검색 (ajax처)
	 * @param category
	 * @param searchWord
	 * @param model
	 * @param request
	 * @return
	 */
	
	@GetMapping("/boardSearch")
	public String boarSearch(@RequestParam(name="category", defaultValue = "all") String category,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			Model model, HttpServletRequest request) {
		
		//log.info("카테고리: {} ",category);
		//log.info("찾을거 : {} ",searchWord);
		
		
		String contextPath =request.getContextPath();
		List<BoardDTO> dtoList = boardService.search(category,searchWord);
		//log.info("@@@@@여기임!!!"+dtoList.toString());

		model.addAttribute("list",dtoList);
		model.addAttribute("contextPath", contextPath);
	
		return "board/boardSearch";	
	}
	
	
	/**
	 * 상품 검색 ajax 처리 
	 * @param category
	 * @param searchWord
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/search")
	@ResponseBody
	public List<BoardDTO> search(@RequestParam(name="category", defaultValue = "all") String category,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			Model model, HttpServletRequest request) {
		
		log.info("카테고리: {} ",category);
		log.info("찾을거 : {} ",searchWord);
		
		
		String contextPath =request.getContextPath();
		List<BoardDTO> dtoList = boardService.search(category,searchWord);
		log.info("@@@@@여기임!!!"+dtoList.toString());

		model.addAttribute("list",dtoList);
		model.addAttribute("contextPath", contextPath);
	
		return dtoList;	
	}
	
	

	
	
	

}
