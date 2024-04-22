package net.kdigital.market.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.kdigital.market.dto.CommentDTO;
import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.CommentEntity;
import net.kdigital.market.repository.BoardRepository;
import net.kdigital.market.repository.CommentRepository;



@Service
@Slf4j
@RequiredArgsConstructor 
public class CommentService {
	
	private final BoardRepository boardRepository;
	private final  CommentRepository commentRepository;
	
	/**
	 * 댓글 반환 함수
	 * @param boardNum
	 * @return
	 */
	public List<CommentDTO> replyAll(Long boardNum) {
		BoardEntity entity = boardRepository.findById(boardNum).get();
		
		
		List<CommentEntity> replyEntityList = commentRepository.findAllByBoardEntityOrderByContentNumDesc(entity);
		List<CommentDTO> replyDTOList = new ArrayList<>();
	

		for(CommentEntity temp : replyEntityList) {
			
			
			CommentDTO dto =CommentDTO.toDTO(temp, boardNum);
			replyDTOList.add(dto);
		}
		
		
		return replyDTOList;

	}
	
	/**
	 * 댓글 입력 
	 * @param commentDTO
	 * @return
	 */
	public CommentDTO replyInsert(CommentDTO commentDTO) {
		

		// 댓글의 부모인 게시글이 존재하는지 확인 
		Optional<BoardEntity> boardEntity = boardRepository.findById(commentDTO.getBoardNum());
		
		if(boardEntity.isPresent()) {
			BoardEntity entity = boardEntity.get(); // dto안네 보드엔터티가 있다는 거 
			CommentEntity replyEntity = CommentEntity.toEntity(commentDTO,entity); // 댓글저장 
			
			CommentEntity temp = commentRepository.save(replyEntity);
			
			return CommentDTO.toDTO(temp, commentDTO.getBoardNum());
			
			}else {
				return null;}
		
	}
	
	/**
	 * 댓글 삭제 
	 * @param replyNum
	 */

	public void replyDelete(Long replyNum) {
		
		commentRepository.deleteById(replyNum);
	

	}
	
	/**
	 *댓글 한개 선택 
	 * @param replyNum
	 * @return
	 */
	public CommentDTO select(Long replyNum) {
		Optional<CommentEntity> entity =commentRepository.findById(replyNum);
		CommentEntity replyEntity = entity.get(); // 엔티티로 바꾸고 
		return CommentDTO.toDTO(replyEntity, replyNum);
		
	}
	
	/**
	 * 댓글수정 
	 * @param replyNum
	 * @param replyText
	 */
	
	@Transactional
	public void update(Long replyNum, String replyText) {
		Optional<CommentEntity> entity =commentRepository.findById(replyNum);
		CommentEntity replyEntity = entity.get(); // 엔티티로 바꾸고 
		replyEntity.setCommentText(replyText);
		
	}

}
