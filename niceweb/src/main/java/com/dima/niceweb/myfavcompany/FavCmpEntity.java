package com.dima.niceweb.myfavcompany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="FAVORITE") // 실제 테이블과 이름이 같으면생략 가능 
public class FavCmpEntity { // 마이페이지 - 찜기능 
	
	@Id
	@Column(name="USER_NUM" , nullable = false) // 회원 고유 num
	private Long userNum;
	
	@Column(name="DUNS_NO" , nullable = false) // 크롤링한 회사 던스넘버 
	private String cmpDunsNo;
	
	public static FavCmpEntity toEntity(FavCmpDTO favCmpDTO) {
		return FavCmpEntity.builder()
				.userNum(favCmpDTO.getUserNum())
				.cmpDunsNo(favCmpDTO.getCmpDunsNo())
				.build();
	}

}
