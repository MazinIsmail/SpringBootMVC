package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.mvc.entity.ValidDataEntity;

@Repository
@Transactional
public interface ValidDataRepository extends JpaRepository<ValidDataEntity, String> {

}
