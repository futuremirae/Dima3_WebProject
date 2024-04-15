package net.kdigital.board.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.dto.BoardDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="board") // 실제 테이블과 이름이 같으면생략 가능 
public class BoardEntity {
	
	
	
	@SequenceGenerator(
			name="boardSeq"
			,sequenceName="board_seq" // 실제 디비 이름 똑같이 
			,initialValue = 1
			,allocationSize = 1
			)
	@Id
	@GeneratedValue(generator = "boardSeq")
	@Column(name="board_num")
	private Long boardNum;
	
	@Column(name="board_writer" ,nullable = false)
	private String boardWriter;
	
	@Column(name="board_title" ,nullable = false)
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(name="hit_count")
	private int hitCount;
	
	@Column(name="favorite_count")
	private int favoriteCount;
	
	@Column(name="create_date")
	@CreationTimestamp  // 게시글이 처음 생성될때 자동으로 날짜 세팅 
	private LocalDateTime createDate;
	
	
	@Column(name="update_date")
	@LastModifiedDate // 게시글이 수정 되었을 떄 마지막 시간과 날짜 
	private LocalDateTime updateDate;
	
	// 첨부 파일이 생성 될때 
	@Column(name="original_file_Name")
	private String originalFileName; // 원본 파일의 파일명  - 저장 되는 것 
	@Column(name="saved_file_Name")
	private String savedFileName; // 하드 디스크상에 저장될 파일명  - 저장 되는 것 
	
	/*
	 * 댓글과의 관계설정 
	 * mappedBy는 one 에해당하는 테이블 엔티티 
	 * CascadeType.REMOVE 이 값으로 on delete cascade를 설정 
	 * fetch : LAZY는 지연호출 , EAGER : 즉시 호출 
	 */
	@OneToMany(mappedBy="boardEntity",
			cascade = CascadeType.REMOVE,
			orphanRemoval = true,
			fetch=FetchType.LAZY
			)
	@OrderBy("reply_num asc") // 댓글들 정렬 방식 
	private List<ReplyEntity> replyEntity = new ArrayList<>(); // 어레이 리스트는 빈이 아니어서필요시마다 불러서 써야함 
	
	
	
	
	//DTO 전달 받아 Entity로 변환 
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.hitCount(boardDTO.getHitCount())
				.favoriteCount(boardDTO.getFavoriteCount())
				.originalFileName(boardDTO.getOriginalFileName())
				.savedFileName(boardDTO.getSavedFileName())
				.build();
		
		
	}

}
