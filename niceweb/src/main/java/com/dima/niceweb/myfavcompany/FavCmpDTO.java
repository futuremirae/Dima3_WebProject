package com.dima.niceweb.myfavcompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FavCmpDTO {
	private Long userNum;
	private String cmpDunsNo;
	
	public static FavCmpDTO toDTO(FavCmpEntity favCmpEntity) {
		return FavCmpDTO.builder()
				.userNum(favCmpEntity.getUserNum()) // 회원 고유 num
				.cmpDunsNo(favCmpEntity.getCmpDunsNo()) //// 크롤링한 회사 던스넘버 
				.build();
	}

}
