package com.nk.Jwt_Impl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.Jwt_Impl.entity.RoleEntity;
import com.nk.Jwt_Impl.enums.EnumRole;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByRoleName(EnumRole eRole);
}
