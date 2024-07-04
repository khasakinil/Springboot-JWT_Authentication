package com.nk.Jwt_Impl.entity;

import com.nk.Jwt_Impl.enums.EnumRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Enumerated
	private EnumRole roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRole getRoleName() {
		return roleName;
	}

	public void setRoleName(EnumRole roleName) {
		this.roleName = roleName;
	}

}
