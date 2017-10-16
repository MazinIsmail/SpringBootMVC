package com.springboot.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.entity.CountDealEntity;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;

@Service
public class StorageServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceHelper.class);

	public List<ValidDataEntity> formValidDataEntity(String validData, List<ValidDataEntity> validDataEntityList,
			String fileName) {
		LOGGER.debug("formValidDataEntity :: Start");
		ValidDataEntity validDataEntity = new ValidDataEntity();
		String[] rowValues = validData.split(SpringBootMVCConstants.CSVSSPITBY);
		validDataEntity.setId(rowValues[0]);
		validDataEntity.setOrderingCurrency(rowValues[1]);
		validDataEntity.setCurrencyIsoCode(rowValues[2]);
		validDataEntity.setTimestmap(rowValues[3]);
		validDataEntity.setDealAmount(rowValues[4]);
		validDataEntity.setSourceFileName(fileName);
		validDataEntityList.add(validDataEntity);
		LOGGER.debug("formValidDataEntity :: End");
		return validDataEntityList;
	}

	public List<InvalidDataEntity> formInvalidDataEntity(String invalidData, String comments,
			List<InvalidDataEntity> invalidDataEntityList, String fileName) {
		LOGGER.debug("formInvalidDataEntity :: Start");
		InvalidDataEntity invalidDataEntity = new InvalidDataEntity();
		invalidDataEntity.setRawData(invalidData.toString());
		invalidDataEntity.setSourceFileName(fileName);
		invalidDataEntity.setComments(comments);
		invalidDataEntityList.add(invalidDataEntity);
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