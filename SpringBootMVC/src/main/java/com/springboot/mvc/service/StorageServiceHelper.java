package com.springboot.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;

@Service
public class StorageServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceHelper.class);

	private static final String cvsSplitBy = ",";

	public List<ValidDataEntity> formValidDataEntity(List<String> validDataList, String fileName) {
		LOGGER.debug("formValidDataEntity :: Start");
		List<ValidDataEntity> validDataEntityList = new ArrayList<ValidDataEntity>();
		for (String validData : validDataList) {
			ValidDataEntity validDataEntity = new ValidDataEntity();
			String[] rowValues = validData.split(cvsSplitBy);
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

	public List<InvalidDataEntity> formInvalidDataEntity(List<String> invalidData, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}