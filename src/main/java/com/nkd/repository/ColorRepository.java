package com.nkd.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nkd.entity.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, Long>{

	List<ColorEntity> findByCodeNotIn(Set<String> code);

	ColorEntity findOneByCode(String colorCode);
}
