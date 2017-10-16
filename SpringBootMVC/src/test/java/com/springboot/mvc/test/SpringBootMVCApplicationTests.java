package com.springboot.mvc.test;

import java.io.InputStream;

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
import com.springboot.mvc.service.StorageService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringBootMVCApplication.class)
@DataJpaTest
public class SpringBootMVCApplicationTests {

	@Autowired
	private StorageService storageService;

	@Test
	public void testFileUpload() throws Exception {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("UploadTest1.csv");
		MultipartFile firstFile = new MockMultipartFile("data", "UploadTest1.csv", "text/plain",
				IOUtils.toByteArray(inputStream));
		storageService.fileUpload(firstFile);
	}

	@Test
	public void contextLoads() {
	}

}
