package net.kdigital.market.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
import net.kdigital.market.dto.BoardDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="market_board")
public class BoardEntity {
	

	@SequenceGenerator(
			name="market_board_seq"
			,sequenceName="market_board_seq" // 실제 디비 이름 똑같이 
			,initialValue = 1
			,allocationSize = 1
			)
	@Id
	@GeneratedValue(generator = "market_board_seq")
	@Column(name="board_num")
	private Long boardNum;
	
	@Column(name="member_id")
	private String boardWriter; // 보드의 유저 아이디 
	
	@Column(name="title")
	private String boardTitle;
	
	@Column(name="contents")
	private String boardContent;
	
	@Column(name="input_date")
	@CreationTimestamp  
	private LocalDateTime createDate;
	
	
	private String category;
	
	@Column(name="soldout")
	private String soldOut;
	
	@Column(name="buyer_id")
	private String buyerId;
	
	@OneToMany(mappedBy="boardEntity",
			cascade = CascadeType.REMOVE,
			orphanRemoval = true,
			fetch=FetchType.LAZY
			)
	@OrderBy("content_num asc") // 댓글들 정렬 방식 
	private List<CommentEntity> commentEntity = new ArrayList<>();
	
public static BoardEntity toEntity(BoardDTO boardDTO) {
		
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.createDate(boardDTO.getCreateDate())
				.category(boardDTO.getCategory())
				.soldOut(boardDTO.getSoldOut())
				.buyerId(boardDTO.getBuyerId())
				.build();
	
	}
	
	

}
