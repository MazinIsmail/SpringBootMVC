package com.springboot.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.mvc.entity.InvalidDataEntity;

@Repository
public interface InvalidDataRepository extends JpaRepository<InvalidDataEntity, String> {

	List<InvalidDataEntity> findBySourceFileName(String fileName);

}
