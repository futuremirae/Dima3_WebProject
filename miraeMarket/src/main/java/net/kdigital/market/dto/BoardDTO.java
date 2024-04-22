package net.kdigital.market.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.entity.BoardEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDTO {
	private Long boardNum;
	private String boardWriter; // 보드의 유저 아이디 
	private String boardTitle;
	private String boardContent;
	private LocalDateTime createDate;
	private String category;
	private String soldOut;
	private String buyerId;
	
public static BoardDTO toDTO(BoardEntity boardEntity) {
		
		return BoardDTO.builder()
				.boardNum(boardEntity.getBoardNum())
				.boardWriter(boardEntity.getBoardWriter())
				.boardTitle(boardEntity.getBoardTitle())
				.boardContent(boardEntity.getBoardContent())
				.createDate(boardEntity.getCreateDate())
				.category(boardEntity.getCategory())
				.soldOut(boardEntity.getSoldOut())
				.buyerId(boardEntity.getBuyerId())
				.build();
		
		
	}


}



