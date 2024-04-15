package net.kdigital.board.dto;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.entity.BoardEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDTO {
	
	private Long boardNum;
	private String boardTitle;
	private String boardWriter; // 보드의 유저 아이디 
	private String boardContent;
	private int hitCount;
	private int favoriteCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	
	// 파일이 첨부되었을 때 
	private MultipartFile uploadFile; // 파일을 덩어리로 받아내기 위한 거 , 
									 //자바의 복잡한 소켓통신을 간편하게 해주는 것 
	
	private String originalFileName; // 원본 파일의 파일명  - 저장 되는 것 
	private String savedFileName; // 하드 디스크상에 저장될 파일명  - 저장 되는 것 
	
	// 생성자 (페이징을 위한 생성자, boardList에서 사용할 내용을 추림 )
	public BoardDTO(Long boardNum, String boardTitle, String boardWriter, int hitCount, LocalDateTime createDate,
			String originalFileName) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.hitCount = hitCount;
		this.createDate = createDate;
		this.originalFileName = originalFileName;
	}
	
	
	
	
	//엔티티를 DTO로  바꾸는 것 
	
public static BoardDTO toDTO(BoardEntity boardEntity) {
		
		return BoardDTO.builder()
				.boardNum(boardEntity.getBoardNum())
				.boardWriter(boardEntity.getBoardWriter())
				.boardTitle(boardEntity.getBoardTitle())
				.boardContent(boardEntity.getBoardContent())
				.hitCount(boardEntity.getHitCount())
				.favoriteCount(boardEntity.getFavoriteCount())
				.createDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.originalFileName(boardEntity.getOriginalFileName())
				.savedFileName(boardEntity.getSavedFileName())
				.build();
		
		
	}


	

}


