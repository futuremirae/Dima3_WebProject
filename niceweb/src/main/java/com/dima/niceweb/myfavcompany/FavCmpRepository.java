package com.dima.niceweb.myfavcompany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dima.niceweb.user.UserEntity;

public interface FavCmpRepository extends JpaRepository<FavCmpEntity, Long> {

	//List<FavCmpEntity> findAllByUserEntityOrderByFavCmpNoDesc(UserEntity userEntity);// 즐겨 착기 저장소 입니다 

	List<FavCmpEntity> findAllByUserEntity(UserEntity userEntity);

//	@Query("select fce.cmpS from FavCmpEntity fce  where fce.userEntity.userNum = :userNum")
//	List<Object[]> wdjifw(@Param("userNum")Long userNume);
}
