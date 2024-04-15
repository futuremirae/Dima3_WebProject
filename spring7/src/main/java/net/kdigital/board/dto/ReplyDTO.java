package net.kdigital.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.board.entity.ReplyEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ReplyDTO {
	private Long replyNum;
	private String replyWriter;
	private String replyText;
	private Long boardNum; 
	private LocalDateTime createDate;
	
	/*Entity -> DTO*/
	public static ReplyDTO toDTO(ReplyEntity replyEntity, Long boardNum) {
		return ReplyDTO.builder()
				.replyNum(replyEntity.getReplyNum())
				.replyWriter(replyEntity.getReplyWriter())
				.replyText(replyEntity.getReplyText())
				.boardNum(boardNum)
				.createDate(replyEntity.getCreateDate())
				.build();
				
	}

}

//reply_num NUMBER PRIMARY KEY
//, board_num NUMBER REFERENCES board(board_num) ON DELETE CASCADE -- 게시글 번
//,reply_writer VARCHAR2(20) --댓글 작성자 
//,reply_text VARCHAR2(1000) NOT NULL -- 댓글내용 
//,create_date DATE DEFAULT sysdate -- 댓글 작성자 

