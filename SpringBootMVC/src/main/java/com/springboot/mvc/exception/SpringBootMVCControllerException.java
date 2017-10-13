package com.springboot.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.mvc.domain.ErrorCode;

@ControllerAdvice
public class SpringBootMVCControllerException {

	@ExceptionHandler(SpringBootMVCException.class)
	public String handleError1(SpringBootMVCException e, RedirectAttributes redirectAttributes) {
		if (e.getErrCode() == ErrorCode.MANDATORY.getCode()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:uploadStatus";
		} else if (e.getErrCode() == ErrorCode.EXISTING.getCode()) {
			redirectAttributes.addFlashAttribute("message", "File has already been uploaded before.");
			return "redirect:uploadStatus";
		}
		redirectAttributes.addFlashAttribute("message", "Something went wrong, will get back to you.");
		return "redirect:uploadStatus";
	}
}
