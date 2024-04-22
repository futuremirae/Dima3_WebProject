package net.kdigital.market.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.market.entity.CommentEntity;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CommentDTO {
	private Long contentNum;
	private Long boardNum;
	private String memberId;
	private String commentText; 
	private LocalDateTime createDate;
	
	public static CommentDTO toDTO(CommentEntity commentEntity, Long boardNum) {
		return CommentDTO.builder()
				.contentNum(commentEntity.getContentNum())
				.boardNum(boardNum)
				.memberId(commentEntity.getMemberId())
				.commentText(commentEntity.getCommentText())
				.createDate(commentEntity.getCreateDate())
				.build();
				
	}

}

