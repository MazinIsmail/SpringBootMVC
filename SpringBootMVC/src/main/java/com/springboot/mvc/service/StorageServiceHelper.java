package com.springboot.mvc.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.domain.DataUploadMapper;
import com.springboot.mvc.domain.ErrorCode;
import com.springboot.mvc.entity.CountDealEntity;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;
import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.validation.ValidateRequest;

@Service
public class StorageServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceHelper.class);

	public ValidDataEntity formValidDataEntity(DataUploadMapper dataUploadMapper, String fileName) {
		LOGGER.debug("formValidDataEntity :: Start");
		ValidDataEntity validDataEntity = new ValidDataEntity();
		validDataEntity.setId(dataUploadMapper.getId());
		validDataEntity.setOrderingCurrency(dataUploadMapper.getOrderingCurrency());
		validDataEntity.setCurrencyIsoCode(dataUploadMapper.getCurrencyIsoCode());
		validDataEntity.setTimestmap(dataUploadMapper.getTimestmap());
		validDataEntity.setDealAmount(dataUploadMapper.getDealAmount());
		validDataEntity.setSourceFileName(fileName);
		LOGGER.debug("formValidDataEntity :: End");
		return validDataEntity;
	}

	public InvalidDataEntity formInvalidDataEntity(DataUploadMapper dataUploadMapper, String fileName) {
		LOGGER.debug("formInvalidDataEntity :: Start");
		InvalidDataEntity invalidDataEntity = new InvalidDataEntity();
		invalidDataEntity.setRawData(dataUploadMapper.getDumb());
		invalidDataEntity.setSourceFileName(fileName);
		invalidDataEntity.setComments(dataUploadMapper.getComments());
		LOGGER.debug("formInvalidDataEntity :: End");
		return invalidDataEntity;
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

	public List<DataUploadMapper> processInputFile(MultipartFile file) throws SpringBootMVCException {
		LOGGER.debug("processInputFile :: Start");
		List<DataUploadMapper> inputList = new ArrayList<DataUploadMapper>();
		try {
			byte[] byteArr = file.getBytes();
			InputStream inputFS = new ByteArrayInputStream(byteArr);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			inputList = br.lines().map(mapToItem).collect(Collectors.toList());
			br.close();
		} catch (Exception e) {
			throw new SpringBootMVCException(ErrorCode.INTERNALSERVERERROR.getCode(),
					ErrorCode.INTERNALSERVERERROR.getDescription());
		}
		LOGGER.debug("processInputFile :: End");
		return inputList;
	}

	private Function<String, DataUploadMapper> mapToItem = (line) -> {
		DataUploadMapper dataUploadMapper = new DataUploadMapper();
		String[] rowData = line.split(SpringBootMVCConstants.CSVSSPITBY);
		String checkValidRow = ValidateRequest.validateSingleRow(rowData);
		if (SpringBootMVCConstants.VALID.equalsIgnoreCase(checkValidRow)) {
			dataUploadMapper.setValidationPass(SpringBootMVCConstants.PASS);
			dataUploadMapper.setId(rowData[0]);
			dataUploadMapper.setOrderingCurrency(rowData[1]);
			dataUploadMapper.setCurrencyIsoCode(rowData[2]);
			dataUploadMapper.setTimestmap(rowData[3]);
			dataUploadMapper.setDealAmount(rowData[4]);
		} else {
			dataUploadMapper.setValidationPass(SpringBootMVCConstants.FAIL);
			dataUploadMapper.setComments(checkValidRow);
			dataUploadMapper.setDumb(rowData.toString());
		}
		return dataUploadMapper;
	};

}