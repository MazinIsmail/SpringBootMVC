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

import com.springboot.mvc.domain.ErrorCode;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;
import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.repository.ValidDataRepository;

@Service
public class StorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

	@Autowired
	private ValidDataRepository validDataRepository;

	@Autowired
	private StorageServiceHelper storageServiceHelper;

	public boolean fileUpload(MultipartFile file) throws SpringBootMVCException {
		LOGGER.debug("fileUpload :: Start");
		String cvsSplitBy = ",";
		boolean uploadcomplete = false;
		BufferedReader br;
		List<String> validDataList = new ArrayList<String>();
		List<String> invalidDataList = new ArrayList<String>();
		try {
			String fileName = file.getOriginalFilename();
			String line = null;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				if (line.split(cvsSplitBy).length == 5) {
					validDataList.add(line);
				} else {
					invalidDataList.add(line);
				}
			}
			List<ValidDataEntity> validDataEntityList = storageServiceHelper.formValidDataEntity(validDataList,
					fileName);
			List<InvalidDataEntity> invalidDataEntity = storageServiceHelper.formInvalidDataEntity(invalidDataList,
					fileName);
			validDataRepository.save(validDataEntityList);
			LOGGER.debug("fileUpload :: End");
			return uploadcomplete;
		} catch (IOException e) {
			throw new SpringBootMVCException(ErrorCode.INTERNALSERVERERROR.getCode(),
					ErrorCode.INTERNALSERVERERROR.getDescription());
		}

	}

}