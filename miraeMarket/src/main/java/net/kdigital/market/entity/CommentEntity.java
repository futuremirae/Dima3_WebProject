package net.kdigital.market.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import net.kdigital.market.dto.CommentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="market_comment")
public class CommentEntity {
	
	@SequenceGenerator(
			name = "market_reply_seq",
			sequenceName = "market_reply_seq",
			initialValue = 1,
			allocationSize = 1
				
			)
	@Id
	@GeneratedValue(generator="market_reply_seq")
	@Column(name="content_num")
	private Long contentNum;

	@Column(name="member_id")
	private String memberId;
	
	@Column(name="comment_text")
	private String commentText; 
	
	@Column(name="input_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY) // 내가 너에게 관계지정(패치지정) , 레이지 로딩이 기본 - 필요할때만 생성! 
	@JoinColumn(name="board_num")
	private BoardEntity boardEntity;
	
	
	
	public static CommentEntity toEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
		return CommentEntity.builder()
				.contentNum(commentDTO.getContentNum())
				.boardEntity(boardEntity)
				.memberId(commentDTO.getMemberId())
				.commentText(commentDTO.getCommentText())
				.createDate(commentDTO.getCreateDate())
				.build();
				
	}

}
