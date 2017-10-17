package com.springboot.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.mvc.exception.SpringBootMVCException;
import com.springboot.mvc.service.StorageService;

@Controller
public class SpringBootMVCController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootMVCController.class);

	@Autowired
	private StorageService storageService;

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	// Upload and save the file to database.
	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws SpringBootMVCException {
		LOGGER.debug("singleFileUpload :: Start");
		storageService.fileUpload(file);
		redirectAttributes.addFlashAttribute("message", "Upload Complete");
		LOGGER.debug("singleFileUpload :: End");
		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

	// REST service to get all the valid data from the database.
	@RequestMapping(value = "/getAllValidData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllValidData(@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Object>(storageService.getAllValidDataEntity(), HttpStatus.OK);
	}

	// REST service to get all the invalid data from the database.
	@RequestMapping(value = "/getAllInvalidData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllInvalidData(@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Object>(storageService.getAllInvalidDataEntity(), HttpStatus.OK);
	}

	// REST service to get all count deal from the database.
	@RequestMapping(value = "/getAllCountDeal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllCountDeal(@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Object>(storageService.getAllCountDealEntity(), HttpStatus.OK);
	}

	// REST service to get all the valid data by filename from the database.
	@RequestMapping(value = "/getAllValidDataByFileName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllValidDataByFileName(@RequestBody String filename,
			@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Object>(storageService.getAllValidDataEntityByFileName(filename), HttpStatus.OK);
	}

	// REST service to get all the invalid data by filename from the database.
	@RequestMapping(value = "/getAllInvalidDataByFileName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllInvalidDataByFileName(@RequestBody String filename,
			@RequestHeader HttpHeaders headers) {
		return new ResponseEntity<Object>(storageService.getAllInvalidDataEntityByFileName(filename), HttpStatus.OK);
	}

}