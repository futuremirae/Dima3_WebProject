package net.kdigital.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.repository.BoardRepository;

@SpringBootTest
class Spring7ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BoardRepository repository; // 인터페이스이므로 new 못함 , 생성자 주입방식도 x -> 테스트 코드는 메소드별로 실행함으로 못한다 
	
	@Test
	void testInsertBoard() {
		
		for (int i = 0; i < 22; i++) {
			BoardEntity b = new BoardEntity(); 
			// BoardEntity는 boardrepository에 직접적으로 데이터를 넣을 때 
			b.setBoardWriter("아메리카노");
			b.setBoardTitle("산미없는원두로");
			
			repository.save(b);
			
		}
	}
}
