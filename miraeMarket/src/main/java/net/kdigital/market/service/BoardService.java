package net.kdigital.market.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.repository.BoardRepository;

@Service
@Slf4j
@RequiredArgsConstructor 
public class BoardService {
	private final BoardRepository boardRepository;

	/**
	 * 상품 등록 
	 * @param boardDTO
	 */
	public void insertproduct(BoardDTO boardDTO) {
		
		boardRepository.save(BoardEntity.toEntity(boardDTO));
		
		
	}

	/**
	 * 판매 중인 상품들 리스트 반
	 * @return
	 */
	public List<BoardDTO> selectAll() {

		List<BoardDTO> boardDTOList = new ArrayList<>();
		List<BoardEntity> entityList = boardRepository.findBySoldOut("N");// 판매중인 상품만 
		entityList.forEach((entity)->boardDTOList.add(BoardDTO.toDTO(entity)));
		
		
		return boardDTOList;
	}
	
	/**
	 * 상품 한개 반환 
	 * @param boardNum
	 * @return
	 */

	public BoardDTO selectOne(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		return BoardDTO.toDTO(entity.get());
	}
	
	/**
	 * 상품 지우기 
	 * @param boardNum
	 */
	public void deleteOne(Long boardNum) {
		boardRepository.deleteById(boardNum);
	}
	
	/**
	 * 상품 검색시 사용 (ajax이용시) 
	 * @param category
	 * @param searchWord
	 * @return
	 */
	public List<BoardDTO> search(String category, String searchWord) {
		// 넘어온 카테고리가 전체일때 
		List<BoardEntity> entityList = new ArrayList<>();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		if("all".equals(category)) {
			
			entityList=boardRepository.findByBoardTitleContaining(searchWord);
		
		}
		else {
			
			entityList =boardRepository.findByCategoryAndBoardTitleContaining(category, searchWord);
			
		}
		entityList.forEach((entity)->boardDTOList.add(BoardDTO.toDTO(entity)));

		return boardDTOList;
	}
	
	/**
	 * 상품 구매 버튼 클릭시, soldout 상태명 바꾸기 
	 * @param boardNum
	 * @param loginId
	 */
	public void buyOne(Long boardNum, String loginId) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		BoardEntity boardEntity = entity.get();
		boardEntity.setBuyerId(loginId);// 바꾸기 -구매 사용자 
		boardEntity.setSoldOut("Y");
		
		
	}

}
