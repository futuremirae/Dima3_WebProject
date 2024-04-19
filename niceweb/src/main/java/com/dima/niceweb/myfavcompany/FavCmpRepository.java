package com.dima.niceweb.myfavcompany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavCmpRepository extends JpaRepository<FavCmpEntity, Long> {// 즐겨 착기 저장소 입니다 

}
