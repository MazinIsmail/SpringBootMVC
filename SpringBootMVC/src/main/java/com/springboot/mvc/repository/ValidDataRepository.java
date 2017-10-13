package com.springboot.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mvc.entity.ValidDataEntity;

@Repository
public interface ValidDataRepository extends JpaRepository<ValidDataEntity, String> {

	@Query("SELECT currencyIsoCode as currencyIsoCodeDistinct, COUNT(*) as dealCount FROM ValidDataEntity GROUP BY currencyIsoCode ORDER BY dealCount DESC")
	List<Object[]> getCountOfDeals();

	List<ValidDataEntity> findBySourceFileName(String fileName);
}
