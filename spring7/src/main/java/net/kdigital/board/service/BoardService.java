package net.kdigital.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.spi.EntityEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.board.dto.BoardDTO;
import net.kdigital.board.entity.BoardEntity;
import net.kdigital.board.repository.BoardRepository;
import net.kdigital.board.util.FileService;

@Service
@Slf4j
@RequiredArgsConstructor   // 멤버중에final있으면 자동으로 주입받게함 
public class BoardService {
	private final BoardRepository boardRepositoty;
	
	// 업로드 된 파일이 저장될 디렉토리 경로를 읽어옴 
	@Value("${spring.servlet.multipart.location}") // annotationvalue로import 
	String uploadPath;
	
	@Value("${user.board.pageLimit}")
	int pageLimit;
	
	
	
	
	/**
	 *전체 리스트, 검색 기능 
	 * @param pageable 
	 * @param searchItem
	 * @param searchWord
	 * @return
	 */
	public Page<BoardDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
		// 사용자가 요청한 page번호 
		int page = pageable.getPageNumber() -1; // jpa는 0페이지부터 가져온다 
		// -1을 하는 이유는 : page 위치의 값은 0부터 시작함 
		// 사용자가1페이지를 요청하면 db에서는 db에서 0페이지를 가져와야함 
		Page<BoardEntity> entityList = null;
		
		
//		//Java Reflection 기능을  이용할 수 도있다.
//		List<BoardEntity> entitylist = null;
//		
		switch(searchItem) {
			case "boardTitle": // 요청페이지, 리밋페이지, 솔트기준 
				entityList = boardRepositoty.findByBoardTitleContaining(searchWord,
						PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.DESC,"boardNum")));
				break;
			case "boardWriter":
				entityList = boardRepositoty.findByBoardWriterContaining(searchWord,
						PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.DESC,"boardNum")));
				break;
			case "boardContent":
				entityList = boardRepositoty.findByBoardContentContaining(searchWord,
						PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.DESC,"boardNum")));
				break;
			
		}
//		System.out.println("글내용 (getContent): "+entityList.getContent());
//		System.out.println("글 개수 (getTotalElements): "+entityList.getTotalElements());
//		System.out.println("요청한 페이지(getNumber): "+entityList.getNumber());
//		System.out.println("총 페이지수 (getTotalPages): "+entityList.getTotalPages());
//		System.out.println("한페이지 글 개수 (getSize==pageLimit ): "+entityList.getSize());
//		System.out.println("이전 페이지가 있니?(hasPrevious): "+entityList.hasPrevious());
//		System.out.println("hasNext: "+entityList.hasNext());
//		System.out.println("isFirst: "+entityList.isFirst());
//		System.out.println("isLast: "+entityList.isLast());
//		
		
		
//		List<BoardEntity> entitylist =boardRepositoty.findAll(Sort.by(Sort.Direction.DESC,"createDate"));
		Page<BoardDTO> dtoList = null; // DTO 생성자 추가 
		
//		entityList.forEach(entity -> dtolist.add(BoardDTO.toDTO(entity)));
		dtoList = entityList.map(board -> 
		new BoardDTO(board.getBoardNum(),
				board.getBoardTitle(),
				board.getBoardWriter(),
				board.getHitCount(),
				board.getCreateDate(),
				board.getOriginalFileName()
				)
		);
		return dtoList;
	}
	/**
	 * DB에 게시글 저장 
	 * @param boardDTO
	 */

	public void insertboard(BoardDTO boardDTO) {
		
		log.info("저장경로 {}",uploadPath);
		// 저장될 경로를 ?? 하드 디스크에 저장 
		
		String originalFileName = null;
		String savedFilename = null;
		
		// 첨부파일이 있으면 파일명 세팅 실시 
		if(!boardDTO.getUploadFile().isEmpty()) {
			savedFilename = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
			originalFileName = boardDTO.getUploadFile().getOriginalFilename(); // 엔티티 한번 갔다가 오는건가 ?? 
			boardDTO.setOriginalFileName(originalFileName);
			boardDTO.setSavedFileName(savedFilename);
		}
	
		
		
	
		// 최종적으로 엔티티로 변환후 db에 저장 
		BoardEntity boardEntity = BoardEntity.toEntity(boardDTO);
		boardRepositoty.save(boardEntity);
		log.info("~~~~");
		
	}
	/**
	 * boardNum에 해당하는 글을 찾아 조회하는 것 
	 * @param boardNum
	 * @return
	 */
	
	public BoardDTO selectOne(Long boardNum) {
		
		Optional<BoardEntity> entity  =boardRepositoty.findById(boardNum);
		if (entity.isPresent()){
			BoardEntity boardEntity  = entity.get();
			BoardDTO dto = BoardDTO.toDTO(boardEntity);
			
			return dto;
		}
		return null;
	}
	
	/**
	 * DB에서 boardNum에 해당하는 글 삭제  
	 * @param boardNum
	 */
	@Transactional
	public void deleteOne(Long boardNum) {
		
		// 글 번호에 해당하는 글을 읽어옴 -> savefilename 을 알아야 하므로 
		Optional<BoardEntity> entity  =boardRepositoty.findById(boardNum); // 쿼리 select 1번 
		if(entity.isPresent()) {	
			
			BoardEntity board = entity.get();
			String savedFileName = board.getSavedFileName();
			log.info("하이룰루루");
			log.info("{}",savedFileName);
			// 첨부파일이 있을 경유 
			if(savedFileName!=null) {
				String fullPath = uploadPath + "/" +savedFileName;
				FileService.deleteFile(fullPath);
			}
			boardRepositoty.deleteById(boardNum); // 쿼리 2	번 -> @Transactional
						
		}
		
		// 첨부된 파일이 
		
		
	}
	/**
	 * DB에 데이터 수정 작업 처리 
	 * @param boardDTO
	 */
	@Transactional
	public void updateOne(BoardDTO boardDTO) {
		
		MultipartFile uploadFile = boardDTO.getUploadFile(); // 업로드~~
		String originalFileName = null;// 새로운 첨부파일이 있을 때 (사용자 업로드 이름)
		String savedFileName = null; // 새로운 첨부파일이 있을 때 
		String oldSavedFileName = null;// 기존 업로드된  첨부파일이 있을 때 
		
		// 첨부x 기존x
		// title과 content는 항상 업그레이드 
		
		// 업로드한 파일이 있는 경우 
		// 파일을 저장장치에 저장하고 이름을 추출한다. 
		if(!uploadFile.isEmpty()) { // 업로드 o
			originalFileName = uploadFile.getOriginalFilename(); // 이름바꾸고 
			savedFileName = FileService.saveFile(uploadFile, uploadPath); // 저장하고 이름바꾸고 
		}
		//DB에서 데이터를 가져옴 
		Optional<BoardEntity> entity = boardRepositoty.findById(boardDTO.getBoardNum());
		if(entity.isPresent()) {
			BoardEntity boardEntity = entity.get();
			oldSavedFileName = boardEntity.getSavedFileName();  // 기존 저장 파일 
			
			// 기존 파일이 있고 업로드한파일도 있다면  
			if(oldSavedFileName != null && !uploadFile.isEmpty()) {
				String fullPath = uploadPath + "/" + oldSavedFileName;
				//기존 파일 삭제 
				FileService.deleteFile(fullPath);
				
				boardEntity.setOriginalFileName(originalFileName); // dto는 바꿔줄필요가 없으니 ~~ 
				boardEntity.setSavedFileName(savedFileName);
				
			}
			//기존 파일은 없고 업로드된 파일은 있을 경우 
			else if(oldSavedFileName == null && !uploadFile.isEmpty()) {
				boardEntity.setOriginalFileName(originalFileName);
				boardEntity.setSavedFileName(savedFileName);
				
			}
			// 첨부파일이 아예 없는 경우  : 파일 처리는하면 안됨 ==> 기존 db를 건드리면안됨 
			// 파일명이 모두 널인 상태로 넘어옴 
			boardEntity.setBoardTitle(boardDTO.getBoardTitle());
			boardEntity.setBoardContent(boardDTO.getBoardContent());
			boardEntity.setUpdateDate(LocalDateTime.now());
			
			
		}
		
	}
	/**
	 * 조회수 증가 
	 * @param boardNum
	 */
	
	@Transactional
	public void incomplementHitcount(Long boardNum) {
		
		Optional<BoardEntity> entity = boardRepositoty.findById(boardNum);
		
		if(entity.isPresent()) {
			
			BoardEntity boardEntity = entity.get();
			boardEntity.setHitCount(boardEntity.getHitCount()+1);
			
		}
		
		
	}
	@Transactional
	public int likeitCount(Long boardNum, int count) {
		// TODO Auto-generated method stub
		Optional<BoardEntity> entity = boardRepositoty.findById(boardNum);
		int like =  0;
		if(entity.isPresent()) {
			
			BoardEntity boardEntity = entity.get();
			boardEntity.setFavoriteCount((boardEntity.getFavoriteCount()+count)); 
			like = boardEntity.getFavoriteCount();
			
			
		}
		return like;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
