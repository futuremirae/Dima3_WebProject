package net.kdigital.board.controller;



import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.service.BoardService;
import net.kdigital.board.service.ReplyService;
import net.kdigital.board.util.PageNavigator;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService boardService; 
	//
	//private ReplyService replyService;
//	public BoardController(ReplyService replyService) {
//		this.replyService = replyService;
//	}// 내가해보는 거 안되면 지우셈 
	
	//생성자 초기화 
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//파일의 저장 경로 
	@Value("${spring.servlet.multipart.location}")
	String uploadpath;
	
	@Value("${user.board.pageLimit}")
	int pageLimit; // 한페이지에 보여줄 글의 개수 
	
	/**
	 * 글 목록 요청 
	 * 1: index에서 넘어올 경우에는 searchItem과 searchWord가 없으므로 기본값 세팅 
	 * 	  - 1페이지를 요청한 것임 
	 * 2: 목록에서 검색하여 넘어올 경우 searchItem과 searchWord가 있다 
	 *    - 1페이지를 요청한 것임 
	 * 3: 목록의 하단에서 페이지를 선택할 경우 선택한 값을 사용 
	 * @return
	 */
	
	@GetMapping("/boardList")
	public String boardList(
			@PageableDefault(page=1) Pageable pageable, 
			// 페이징을 해주는 객체로, 요청한 페이지가 없으면 1로 세팅, 기본값은 1로 세팅 - 현재 페이지!
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			
		Model model, HttpServletRequest request) { //&&&&&&&&&&
		
		//List<BoardDTO> dtolist = boardService.selectAll(searchItem,searchWord);
		Page<BoardDTO> dtoList = boardService.selectAll(pageable,searchItem,searchWord); // 페이지 타입 반환 
		
		String contextPath =request.getContextPath();
		int totalPages = (int)dtoList.getTotalElements();
		int page = pageable.getPageNumber();
		PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);
		// 이전에 검색했던 내용이 보이게 할 수 있도록 하기 위해서 
		model.addAttribute("list",dtoList);
		model.addAttribute("searchItem",searchItem);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("navi",navi);
		model.addAttribute("contextPath",contextPath);
		
		
		
		
		
		return "board/boardList";
	}
	
	/** 
	 * 글쓰기 화면 요청 
	 * @return
	 */
	@GetMapping("/boardWrite")
	public String boardWrite() {
		log.info("글쓰기 화면 요청!");
		return "board/boardWrite";
		
	}
	/**
	 * 글쓰기 등록 요청 
	 * @return
	 */
	@PostMapping("/boardWrite")
	public String boardWrite(
			@ModelAttribute BoardDTO boardDTO) {
		log.info("글 DB 저장 요청!"+boardDTO.toString());
//		log.info("첨부파일명 : {}",boardDTO.getUploadFile().getOriginalFilename());
//		log.info("첨부파일 사이즈 : {}",boardDTO.getUploadFile().getSize());
		boardService.insertboard(boardDTO);
		
		return "redirect:/board/boardList";
		
	}
	/**
	 * boardNum에  해당하는 글 한개 조회 
	 * @param boardNum
	 * @param model
	 * @return
	 */
	
	@GetMapping("/boardDetail")
	public String boardDetail(
			@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			HttpServletRequest request , 
			Model model) {
		
		
		BoardDTO boardDTO =boardService.selectOne(boardNum);//selectOne는 컨트롤러에서 하기 
		// 조회수 증가 
		boardService.incomplementHitcount(boardNum);
		String contextPath =request.getContextPath();
		System.out.println("==========="+contextPath);
		
		model.addAttribute("board", boardDTO);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("contextPath", contextPath); // 컨텍스트 패스를 넘겨준다 
		
		return "board/boardDetail";
		
	}
	
	/**
	 * boardNum에 해당하는 글을 삭제하기 위한 요청  
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			RedirectAttributes rttr) {
		
		
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		boardService.deleteOne(boardNum);
		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		rttr.addAttribute("boardNum", boardDTO.getBoardNum());
		
		return "redirect:/board/boardList";
		
	}
	/**
	 * boardNum에 해당하는 글을 업데이트 하기 위한 요청  
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
			
			Model model) { // 왜 모델로 담아오는지에 대하여 
		
		log.info("+++++ㅎㅎㅎㅎㅎㅎㅎㅎ+");
		log.info(boardNum.toString(),searchItem.toString(),searchWord.toString());
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		model.addAttribute("board", boardDTO);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
		return "board/boardUpdate";
		
	}
	
	/**
	 * 파라미터로 전달받은 BoardDTO 받아 
	 * 서비스 단으로 전달 
	 * @param boardDTO
	 * @param rttr
	 * @return
	 */
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute BoardDTO boardDTO,
			@RequestParam(name="boardNum") Long boardNum,
			@RequestParam(name="searchItem", defaultValue = "boardTitle") String searchItem,
			@RequestParam(name="searchWord", defaultValue = "") String searchWord,
		
			RedirectAttributes rttr) {
		log.info("=================",boardDTO.toString());
		log.info("+++++포스트~~~~~~~~~~ㅎㅎㅎㅎㅎㅎㅎ+");
		log.info(boardNum.toString()+searchItem+searchWord+"ㅎㅇㅎㄴ");
		boardService.updateOne(boardDTO);

		
		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		rttr.addAttribute("boardNum", boardDTO.getBoardNum());
		return "redirect:/board/boardDetail"; // 알아서 가는건가??
		
	}
	/**
	 * 전달 받은 게시판 번호의 파일을 다운로드 
	 */
	@GetMapping("/download")
	public String download(
			@RequestParam(name="boardNum") Long boardNum,
			HttpServletResponse response) { //HttpServletResponse는 Java Servlet API에서 제공하는 인터페이스로, 웹 애플리케이션에서 HTTP 응답을 나타냅니다. 클라이언트로부터의 HTTP 요청에 대한 응답을 생성하고 조작하는 데 사용됩니다.
		// 실제 파일명을 가지고 와야짐 - savedfilename , original name 
		BoardDTO boardDTO = boardService.selectOne(boardNum);
		String originalFilename = boardDTO.getOriginalFileName();
		String savedFilename = boardDTO.getSavedFileName();
		
		log.info("오리지널: {} ",originalFilename);
		log.info("저장명 : {} ",savedFilename);
		log.info("경로명 : {} ",uploadpath);
		
		// 사용자 입장에서 봤을때 오리지널 파일명을 돌려주는데!
		try {
			String tempName = URLEncoder.encode( // 한글 다운로드시 한글이 깨져 보일 수 있음 방지하기 위해서 변환하는 작
					originalFilename,
					StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment;filename="+tempName); //// attachment파일이 있으므로 다운을 받아라~~
			// 사진을 다운로드 하려고 하는 목적 / 이 코드가 없으면 브라우저 자체에서 오픈을 해버림 -> 대표적으로 이미지 
		} catch (UnsupportedEncodingException e) { // 무조건 익셉션을 잡아라 
			
			e.printStackTrace();
		}
		String fullPath = uploadpath +"/"+savedFilename;
		//스트림 설정 (실제 다운로드) 메모리를 올리는 작
		FileInputStream filein = null; // 로컬 
		ServletOutputStream fileout = null; // 로컬 벗어남 
		
		try {
			// 하드 디스크에서 가져옴 
			filein = new FileInputStream(fullPath); //하드디스크에서 메모리에 올리기 위한 객체 생성 
			fileout = response.getOutputStream(); // 로컬에서 일어나는 작업이 아닌, 원격지에 데이터를 쏴주는 것 / 응답 객체의 getput을 이용-> 로컬에서 벗어나 다른쪽에 데이터를 쏠 수 있게 해줌 
			
			FileCopyUtils.copy(filein, fileout); // 반복적으로 read write (filein에 있는걸 읽어서 fileout에 써)
			fileout.close(); // 순서는 연것의 반대로 닫는다 
			filein.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	@GetMapping("boardlikeit")
	@ResponseBody
	public int boardlikeit(@RequestParam(name="boardNum") Long boardNum,
							@RequestParam(name="count") int count) {
		int like = boardService.likeitCount(boardNum,count);
		
		return like;
		
	}
	
	
	
	

}
