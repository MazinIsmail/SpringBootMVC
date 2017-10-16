package com.springboot.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.domain.DataUploadMapper;
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

			List<DataUploadMapper> dataUploadMapperList = new ArrayList<DataUploadMapper>();
			List<ValidDataEntity> validDataEntityList = new ArrayList<ValidDataEntity>();
			List<InvalidDataEntity> invalidDataEntityList = new ArrayList<InvalidDataEntity>();
			dataUploadMapperList = storageServiceHelper.processInputFile(file);
			for (DataUploadMapper dataUploadMapper : dataUploadMapperList) {
				if (dataUploadMapper.getValidationPass().equalsIgnoreCase(SpringBootMVCConstants.PASS)) {
					ValidDataEntity validDataEntity = storageServiceHelper.formValidDataEntity(dataUploadMapper,
							fileName);
					validDataEntityList.add(validDataEntity);
				} else {
					InvalidDataEntity invalidDataEntity = storageServiceHelper.formInvalidDataEntity(dataUploadMapper,
							fileName);
					invalidDataEntityList.add(invalidDataEntity);
				}
			}
			validDataRepository.save(validDataEntityList);
			invalidDataRepository.save(invalidDataEntityList);
			List<CountDealEntity> countDealEntityList = storageServiceHelper
					.formCountDealEntity(validDataRepository.getCountOfDeals());
			countDealRepository.save(countDealEntityList);
			LOGGER.debug("fileUpload :: End");
		} catch (Exception e) {
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