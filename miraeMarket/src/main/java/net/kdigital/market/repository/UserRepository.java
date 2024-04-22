package net.kdigital.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.market.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByUserId(String userId);

}
