package com.springboot.mvc.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mvc.config.SpringBootMVCApplication;
import com.springboot.mvc.entity.CountDealEntity;
import com.springboot.mvc.entity.InvalidDataEntity;
import com.springboot.mvc.entity.ValidDataEntity;
import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.service.StorageService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringBootMVCApplication.class)
@DataJpaTest
public class SpringBootMVCApplicationTests {

	@Autowired
	private StorageService storageService;

	@Test
	public void fileUploadValidInvalid() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("UploadTest - Valid & Invalid.csv");
		MultipartFile firstFile = new MockMultipartFile("data", "UploadTest - Valid & Invalid.csv", "text/plain",
				IOUtils.toByteArray(inputStream));
		storageService.fileUpload(firstFile);
	}

	@Test(expected = SpringBootMVCException.class)
	public void fileUploadValidInvalidSameFile() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("UploadTest - Valid & Invalid.csv");
		MultipartFile firstFile = new MockMultipartFile("data", "UploadTest - Valid & Invalid.csv", "text/plain",
				IOUtils.toByteArray(inputStream));
		storageService.fileUpload(firstFile);
		storageService.fileUpload(firstFile);
	}

	@Test(expected = SpringBootMVCException.class)
	public void fileUploadValidStress() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("UploadTest - Empty.csv");
		MultipartFile firstFile = new MockMultipartFile("data", "UploadTest - Empty.csv", "text/plain",
				IOUtils.toByteArray(inputStream));
		storageService.fileUpload(firstFile);
	}

	@Test
	public void getAllValidDataTest() throws Exception {
		fileUploadValidInvalid();
		List<ValidDataEntity> validDataEntityListActual = storageService.getAllValidDataEntity();
		List<ValidDataEntity> validDataEntityListExpected = formValidDataEntityListExpected();
		assertTrue(validDataEntityListActual.size() == validDataEntityListExpected.size());
	}

	@Test
	public void getAllValidDataByFileNameTest() throws Exception {
		fileUploadValidInvalid();
		String filename = "UploadTest - Valid & Invalid.csv";
		List<ValidDataEntity> validDataEntityListActual = storageService.getAllValidDataEntityByFileName(filename);
		List<ValidDataEntity> validDataEntityListExpected = formValidDataEntityListExpected();
		assertTrue(validDataEntityListActual.size() == validDataEntityListExpected.size());
	}

	@Test
	public void getAllValidDataByFileNameEmptyTest() throws Exception {
		fileUploadValidInvalid();
		String filename = "Empty.csv";
		List<ValidDataEntity> validDataEntityListActual = storageService.getAllValidDataEntityByFileName(filename);
		assertNull(validDataEntityListActual);
	}

	@Test
	public void getAllInvalidDataTest() throws Exception {
		fileUploadValidInvalid();
		List<InvalidDataEntity> invalidDataEntityList = storageService.getAllInvalidDataEntity();
		assertTrue(invalidDataEntityList.size() == 10);
	}

	@Test
	public void getAllInvalidDataByFileNameTest() throws Exception {
		fileUploadValidInvalid();
		String filename = "UploadTest - Valid & Invalid.csv";
		List<InvalidDataEntity> invalidDataEntityList = storageService.getAllInvalidDataEntityByFileName(filename);
		assertTrue(invalidDataEntityList.size() == 10);
	}

	@Test
	public void getAllInvalidDataByFileNameEmptyTest() throws Exception {
		fileUploadValidInvalid();
		String filename = "Empty.csv";
		List<InvalidDataEntity> invalidDataEntityList = storageService.getAllInvalidDataEntityByFileName(filename);
		assertNull(invalidDataEntityList);
	}

	@Test
	public void getAllCountDealTest() throws Exception {
		fileUploadValidInvalid();
		List<CountDealEntity> countDealEntityList = storageService.getAllCountDealEntity();
		assertTrue(countDealEntityList.size() == 2);
	}

	@Test
	public void contextLoads() {
	}

	private List<ValidDataEntity> formValidDataEntityListExpected() {
		List<ValidDataEntity> ValidDataEntityList = new ArrayList<>();
		ValidDataEntity validDataEntity = new ValidDataEntity();
		validDataEntity.setId("1");
		validDataEntity.setCurrencyIsoCode("IND");
		validDataEntity.setDealAmount("100000");
		validDataEntity.setOrderingCurrency("Rupee");
		validDataEntity.setSourceFileName("1");
		validDataEntity.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity);

		ValidDataEntity validDataEntity2 = new ValidDataEntity();
		validDataEntity2.setId("2");
		validDataEntity2.setCurrencyIsoCode("IND");
		validDataEntity2.setDealAmount("100000");
		validDataEntity2.setOrderingCurrency("Rupee");
		validDataEntity2.setSourceFileName("1");
		validDataEntity2.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity2);

		ValidDataEntity validDataEntity3 = new ValidDataEntity();
		validDataEntity3.setId("3");
		validDataEntity3.setCurrencyIsoCode("IND");
		validDataEntity3.setDealAmount("100000");
		validDataEntity3.setOrderingCurrency("Rupee");
		validDataEntity3.setSourceFileName("1");
		validDataEntity3.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity3);

		ValidDataEntity validDataEntity4 = new ValidDataEntity();
		validDataEntity4.setId("4");
		validDataEntity4.setCurrencyIsoCode("IND");
		validDataEntity4.setDealAmount("100000");
		validDataEntity4.setOrderingCurrency("Rupee");
		validDataEntity4.setSourceFileName("1");
		validDataEntity4.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity4);

		ValidDataEntity validDataEntity5 = new ValidDataEntity();
		validDataEntity5.setId("5");
		validDataEntity5.setCurrencyIsoCode("IND");
		validDataEntity5.setDealAmount("100000");
		validDataEntity5.setOrderingCurrency("Rupee");
		validDataEntity5.setSourceFileName("1");
		validDataEntity5.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity5);

		ValidDataEntity validDataEntity6 = new ValidDataEntity();
		validDataEntity6.setId("6");
		validDataEntity6.setCurrencyIsoCode("IND");
		validDataEntity6.setDealAmount("100000");
		validDataEntity6.setOrderingCurrency("Rupee");
		validDataEntity6.setSourceFileName("1");
		validDataEntity6.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity6);

		ValidDataEntity validDataEntity7 = new ValidDataEntity();
		validDataEntity7.setId("7");
		validDataEntity7.setCurrencyIsoCode("IND");
		validDataEntity7.setDealAmount("100000");
		validDataEntity7.setOrderingCurrency("Rupee");
		validDataEntity7.setSourceFileName("1");
		validDataEntity7.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity7);

		ValidDataEntity validDataEntity8 = new ValidDataEntity();
		validDataEntity8.setId("8");
		validDataEntity8.setCurrencyIsoCode("IND");
		validDataEntity8.setDealAmount("100000");
		validDataEntity8.setOrderingCurrency("Rupee");
		validDataEntity8.setSourceFileName("1");
		validDataEntity8.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity8);

		ValidDataEntity validDataEntity9 = new ValidDataEntity();
		validDataEntity9.setId("9");
		validDataEntity9.setCurrencyIsoCode("IND");
		validDataEntity9.setDealAmount("100000");
		validDataEntity9.setOrderingCurrency("Rupee");
		validDataEntity9.setSourceFileName("1");
		validDataEntity9.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity9);

		ValidDataEntity validDataEntity10 = new ValidDataEntity();
		validDataEntity10.setId("10");
		validDataEntity10.setCurrencyIsoCode("IND");
		validDataEntity10.setDealAmount("100000");
		validDataEntity10.setOrderingCurrency("Rupee");
		validDataEntity10.setSourceFileName("1");
		validDataEntity10.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity10);

		ValidDataEntity validDataEntity11 = new ValidDataEntity();
		validDataEntity11.setId("11");
		validDataEntity11.setCurrencyIsoCode("IND");
		validDataEntity11.setDealAmount("100000");
		validDataEntity11.setOrderingCurrency("Rupee");
		validDataEntity11.setSourceFileName("1");
		validDataEntity11.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity11);

		ValidDataEntity validDataEntity12 = new ValidDataEntity();
		validDataEntity12.setId("12");
		validDataEntity12.setCurrencyIsoCode("IND");
		validDataEntity12.setDealAmount("100000");
		validDataEntity12.setOrderingCurrency("Rupee");
		validDataEntity12.setSourceFileName("1");
		validDataEntity12.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity12);

		ValidDataEntity validDataEntity13 = new ValidDataEntity();
		validDataEntity13.setId("13");
		validDataEntity13.setCurrencyIsoCode("IND");
		validDataEntity13.setDealAmount("100000");
		validDataEntity13.setOrderingCurrency("Rupee");
		validDataEntity13.setSourceFileName("1");
		validDataEntity13.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity13);

		ValidDataEntity validDataEntity14 = new ValidDataEntity();
		validDataEntity14.setId("14");
		validDataEntity14.setCurrencyIsoCode("IND");
		validDataEntity14.setDealAmount("100000");
		validDataEntity14.setOrderingCurrency("Rupee");
		validDataEntity14.setSourceFileName("1");
		validDataEntity14.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity14);

		ValidDataEntity validDataEntity15 = new ValidDataEntity();
		validDataEntity15.setId("15");
		validDataEntity15.setCurrencyIsoCode("IND");
		validDataEntity15.setDealAmount("100000");
		validDataEntity15.setOrderingCurrency("Rupee");
		validDataEntity15.setSourceFileName("1");
		validDataEntity15.setTimestmap("1/1/1970 0:00");
		ValidDataEntityList.add(validDataEntity15);

		return ValidDataEntityList;
	}

}
