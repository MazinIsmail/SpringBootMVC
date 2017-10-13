package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.mvc.entity.CountDealEntity;

@Repository
public interface CountDealRepository extends JpaRepository<CountDealEntity, String> {

}
