package com.nk.Jwt_Impl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.Jwt_Impl.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
	boolean existsByEmail(String email);
}
