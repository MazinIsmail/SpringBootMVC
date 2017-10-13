package com.springboot.mvc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.domain.ErrorCode;
import com.springboot.mvc.entity.CountDealEntity;
import com.springboot.mvc.entity.FileListEntity;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;
import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.repository.CountDealRepository;
import com.springboot.mvc.repository.FileListRepository;
import com.springboot.mvc.repository.InvalidDataRepository;
import com.springboot.mvc.repository.ValidDataRepository;
import com.springboot.mvc.validation.ValidateRequest;

@Service
public class StorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

	@Autowired
	private ValidDataRepository validDataRepository;

	@Autowired
	private InvalidDataRepository invalidDataRepository;

	@Autowired
	private StorageServiceHelper storageServiceHelper;

	@Autowired
	private FileListRepository fileListRepository;

	@Autowired
	private CountDealRepository countDealRepository;

	public void fileUpload(MultipartFile file) throws SpringBootMVCException {
		LOGGER.debug("fileUpload :: Start");
		BufferedReader br;
		List<String> validDataList = new ArrayList<String>();
		List<String> invalidDataList = new ArrayList<String>();
		try {
			ValidateRequest.validateFile(file);
			String fileName = file.getOriginalFilename();
			FileListEntity fileListEntity = fileListRepository.findBySourceFileName(fileName);
			if (fileListEntity == null) {
				FileListEntity fileListEntityAdd = new FileListEntity();
				fileListEntityAdd.setSourceFileName(fileName);
				fileListRepository.save(fileListEntityAdd);
			} else {
				throw new SpringBootMVCException(ErrorCode.EXISTING.getCode(), ErrorCode.EXISTING.getDescription());
			}
			String line = null;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				if (line.split(SpringBootMVCConstants.cvsSplitBy).length == 5) {
					validDataList.add(line);
				} else {
					invalidDataList.add(line);
				}
			}
			List<ValidDataEntity> validDataEntityList = storageServiceHelper.formValidDataEntity(validDataList,
					fileName);
			List<InvalidDataEntity> invalidDataEntityList = storageServiceHelper.formInvalidDataEntity(invalidDataList,
					fileName);
			validDataRepository.save(validDataEntityList);
			invalidDataRepository.save(invalidDataEntityList);
			List<CountDealEntity> countDealEntityList = storageServiceHelper
					.formCountDealEntity(validDataRepository.getCountOfDeals());
			countDealRepository.save(countDealEntityList);
			LOGGER.debug("fileUpload :: End");
		} catch (IOException e) {
			throw new SpringBootMVCException(ErrorCode.INTERNALSERVERERROR.getCode(),
					ErrorCode.INTERNALSERVERERROR.getDescription());
		}

	}

	public List<ValidDataEntity> getAllValidDataEntity() {
		return validDataRepository.findAll();
	}

	public List<InvalidDataEntity> getAllInvalidDataEntity() {
		return invalidDataRepository.findAll();
	}

	public List<CountDealEntity> getAllCountDealEntity() {
		return countDealRepository.findAll();
	}

	public List<ValidDataEntity> getAllValidDataEntityByFileName(String fileName) {
		return validDataRepository.findBySourceFileName(fileName);
	}

	public List<InvalidDataEntity> getAllInvalidDataEntityByFileName(String fileName) {
		return invalidDataRepository.findBySourceFileName(fileName);
	}

}