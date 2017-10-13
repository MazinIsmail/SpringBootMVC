package com.springboot.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.entity.CountDealEntity;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;

@Service
public class StorageServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceHelper.class);

	public List<ValidDataEntity> formValidDataEntity(List<String> validDataList, String fileName) {
		LOGGER.debug("formValidDataEntity :: Start");
		List<ValidDataEntity> validDataEntityList = new ArrayList<ValidDataEntity>();
		for (String validData : validDataList) {
			ValidDataEntity validDataEntity = new ValidDataEntity();
			String[] rowValues = validData.split(SpringBootMVCConstants.cvsSplitBy);
			validDataEntity.setId(rowValues[0]);
			validDataEntity.setOrderingCurrency(rowValues[1]);
			validDataEntity.setCurrencyIsoCode(rowValues[2]);
			validDataEntity.setTimestmap(rowValues[3]);
			validDataEntity.setDealAmount(rowValues[4]);
			validDataEntity.setSourceFileName(fileName);
			validDataEntityList.add(validDataEntity);
		}
		LOGGER.debug("formValidDataEntity :: End");
		return validDataEntityList;
	}

	public List<InvalidDataEntity> formInvalidDataEntity(List<String> invalidDataList, String fileName) {
		LOGGER.debug("formInvalidDataEntity :: Start");
		List<InvalidDataEntity> invalidDataEntityList = new ArrayList<InvalidDataEntity>();
		for (String invalidData : invalidDataList) {
			InvalidDataEntity invalidDataEntity = new InvalidDataEntity();
			invalidDataEntity.setRawData(invalidData.toString());
			invalidDataEntity.setSourceFileName(fileName);
			invalidDataEntityList.add(invalidDataEntity);
		}
		LOGGER.debug("formInvalidDataEntity :: End");
		return invalidDataEntityList;
	}

	public List<CountDealEntity> formCountDealEntity(List<Object[]> validDataEntityObjectList) {
		LOGGER.debug("formCountDealEntity :: Start");
		List<CountDealEntity> countDealEntityList = new ArrayList<CountDealEntity>();
		for (Object[] objects : validDataEntityObjectList) {
			CountDealEntity countDealEntity = new CountDealEntity();
			countDealEntity.setCurrencyIsoCode(objects[0].toString());
			countDealEntity.setCountOfDeal(objects[1].toString());
			countDealEntityList.add(countDealEntity);
		}
		LOGGER.debug("formCountDealEntity :: End");
		return countDealEntityList;
	}

}