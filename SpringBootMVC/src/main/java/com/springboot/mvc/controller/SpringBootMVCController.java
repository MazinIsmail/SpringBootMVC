package com.springboot.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}