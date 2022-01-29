package com.gmail.podkutin.dmitry.bookconverter.controller;

import com.gmail.podkutin.dmitry.bookconverter.util.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//https://spring.io/guides/gs/serving-web-content/
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

	@ExceptionHandler({ValidationException.class})
	public String databaseError(Exception exception, Model model,HttpServletRequest req) {
		model.addAttribute("exception", exception);
		model.addAttribute("url", req.getRequestURL());
		model.addAttribute("timestamp", new Date().toString());
		model.addAttribute("status", 500);
		model.addAttribute("message",exception.getMessage());
		return "validationError";
	}
}