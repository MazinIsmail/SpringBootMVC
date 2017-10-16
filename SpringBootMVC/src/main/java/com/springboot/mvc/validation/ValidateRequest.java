package com.springboot.mvc.validation;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.constants.SpringBootMVCConstants;
import com.springboot.mvc.domain.ErrorCode;
import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.util.SpringBootMVCUtil;

public class ValidateRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateRequest.class);

	// Check if the file is empty or not
	public static void validateFile(MultipartFile file) throws SpringBootMVCException {
		LOGGER.debug("formValidDataEntity :: Start");
		if (file.isEmpty()) {
			throw new SpringBootMVCException(ErrorCode.MANDATORY.getCode(), ErrorCode.MANDATORY.getDescription());
		}
		LOGGER.debug("formValidDataEntity :: End");
	}

	// Used to validate a single row of data of csv file. Did not use regex to
	// improve the performance.
	public static String validateSingleRow(String[] rowData) {
		// This variable holds all the validation comments
		StringBuffer validationComments = new StringBuffer();
		if (rowData.length == 5) {
			// Validating Id Field.
			if (!org.springframework.util.StringUtils.isEmpty(rowData[0])) {
				// Check if the value is a positive integer.
				if (!SpringBootMVCUtil.isStringInt(rowData[0]) || Integer.valueOf(rowData[0]) <= 0) {
					validationComments.append(" ").append(SpringBootMVCConstants.ID_NOT_INTEGER);
				}
			} else {
				validationComments.append(" ").append(SpringBootMVCConstants.ID_EMPTY);
			}
			// Validating Ordering Currency Field.
			if (!org.springframework.util.StringUtils.isEmpty(rowData[1])) {
				// Check if the value has only letters.
				if (!SpringBootMVCUtil.isAlpha(rowData[1])) {
					validationComments.append(" ").append(SpringBootMVCConstants.CURRENCY_CHARATOR);
				}
			} else {
				validationComments.append(" ").append(SpringBootMVCConstants.ORDERING_CURRENCY_EMPTY);
			}
			// Validating Currency ISO Field.
			if (!org.springframework.util.StringUtils.isEmpty(rowData[2])) {
				// Check if the ISO 3 is valid or not.
				Locale localeCheck = SpringBootMVCUtil.initCountryCodeMapping().get(rowData[2]);
				if (null == localeCheck) {
					validationComments.append(" ").append(SpringBootMVCConstants.CURRENCYISO_3_INVALID);
				}
			} else {
				validationComments.append(" ").append(SpringBootMVCConstants.CURRENCYISO_EMPTY);
			}
			// Validating Deal Amount Field.
			if (!org.springframework.util.StringUtils.isEmpty(rowData[4])) {
				// Check if the value is a integer.
				if (!SpringBootMVCUtil.isStringInt(rowData[4]) || Integer.valueOf(rowData[4]) < 0) {
					validationComments.append(" ").append(SpringBootMVCConstants.AMOUNT_NOT_INTEGER);
				}
			} else {
				validationComments.append(" ").append(SpringBootMVCConstants.AMOUNT_EMPTY);
			}
		} else {
			validationComments.append(" ").append(SpringBootMVCConstants.FIELD_IS_MISSING);
		}

		if (org.springframework.util.StringUtils.isEmpty(validationComments.toString())) {
			return SpringBootMVCConstants.VALID;
		} else {
			return validationComments.toString().trim();
		}
	}

}
