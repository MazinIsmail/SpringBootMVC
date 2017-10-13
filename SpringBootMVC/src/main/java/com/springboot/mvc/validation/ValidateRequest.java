package com.springboot.mvc.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.domain.ErrorCode;
import com.springboot.mvc.exception.SpringBootMVCException;

public class ValidateRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateRequest.class);

	public static void validateFile(MultipartFile file) throws SpringBootMVCException {
		LOGGER.debug("formValidDataEntity :: Start");
		if (file.isEmpty()) {
			throw new SpringBootMVCException(ErrorCode.MANDATORY.getCode(), ErrorCode.MANDATORY.getDescription());
		}
		LOGGER.debug("formValidDataEntity :: End");
	}

}
