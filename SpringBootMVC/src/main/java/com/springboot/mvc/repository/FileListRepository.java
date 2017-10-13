package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.mvc.entity.FileListEntity;

@Repository
public interface FileListRepository extends JpaRepository<FileListEntity, String> {

	FileListEntity findBySourceFileName(String file);

}
