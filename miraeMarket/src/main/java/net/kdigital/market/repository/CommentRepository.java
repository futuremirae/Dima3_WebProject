package net.kdigital.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.BoardEntity;
import net.kdigital.market.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findAllByBoardEntityOrderByContentNumDesc(BoardEntity entity);

}
