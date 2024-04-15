package net.kdigital.board.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.ReplyDTO;
import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.entity.ReplyEntity;
import net.kdigital.board.repository.BoardRepository;
import net.kdigital.board.repository.ReplyRepository;

@Service //
@RequiredArgsConstructor //일반적으로 Java 클래스에서는 필드를 정의하고, 그 필드를 초기화하기 위한 생성자를 명시적으로 작성해야 합니다. 그러나 Lombok의 @RequiredArgsConstructor를 사용하면 이러한 생성자를 자동으로 생성할 수 있습니다.
@Slf4j
public class ReplyService {
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;


	public ReplyDTO replyInsert(ReplyDTO replyDTO) {
		
		// 댓글의 부모인 게시글이 존재하는지 확인 
		Optional<BoardEntity> boardEntity = boardRepository.findById(replyDTO.getBoardNum());
		
		if(boardEntity.isPresent()) {
			BoardEntity entity = boardEntity.get(); // dto안네 보드엔터티가 있다는 거 
			ReplyEntity replyEntity = ReplyEntity.toEntity(replyDTO,entity); // 댓글저장 
			
			ReplyEntity temp = replyRepository.save(replyEntity);
			
			return ReplyDTO.toDTO(temp, replyDTO.getBoardNum());
			
			
			}else {
				return null;}
		
	}
	/**
	 * 댓글 전체 목록 반환 
	 * @param boardNum
	 * @return
	 */


	public List<ReplyDTO> replyAll(Long boardNum) {
		
		BoardEntity entity = boardRepository.findById(boardNum).get();
		
		// 그냥boardNum  해당 값에 해당하는 거만 꺼내오면 안되는 건가 ?? 
		
		List<ReplyEntity> replyEntityList = replyRepository.findAllByBoardEntityOrderByReplyNumDesc(entity);
		List<ReplyDTO> replyDTOList = new ArrayList<>();
	
		//replyEntityList.forEach(item->replyDTOList.add(ReplyDTO.toDTO(item, boardNum)));
		for(ReplyEntity temp : replyEntityList) {
			
			ReplyDTO dto =ReplyDTO.toDTO(temp, boardNum);
			replyDTOList.add(dto);
		}
		
		
		return replyDTOList;
	}
	public void replyDelete(Long replyNum) {
		log.info("지운느거 한번 해볼까용???"+replyNum);
		
		replyRepository.deleteById(replyNum);
		log.info("삭제 완료!!"+replyNum);
		
		
	}
	public ReplyDTO select(Long replyNum) {
		Optional<ReplyEntity> entity =replyRepository.findById(replyNum);
		ReplyEntity replyEntity = entity.get(); // 엔티티로 바꾸고 
		return ReplyDTO.toDTO(replyEntity, replyNum);
	}
	@Transactional
	public void update(Long replyNum, String replyText) {
		Optional<ReplyEntity> entity =replyRepository.findById(replyNum);
		ReplyEntity replyEntity = entity.get(); // 엔티티로 바꾸고 
		replyEntity.setReplyText(replyText);
	}

}
