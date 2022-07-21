package com.nkd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findOneByCode(String item);

}
