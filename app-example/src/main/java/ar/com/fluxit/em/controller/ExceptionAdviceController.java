package ar.com.fluxit.em.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.LogManager;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.Status;
import ar.com.fluxit.em.client.ErrorManagerImpl;




@Controller
@ControllerAdvice
class ExceptionAdviceController {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	private ErrorManagerImpl errorManager;

	public ExceptionAdviceController() {
		 errorManager = new ErrorManagerImpl("ef79164d-d8c9-4d3e-b7b5-b1a010094d95", "http://localhost:8080/em-server/registerError");

	}
	
	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView exception(Throwable throwable,
			HttpServletRequest request) {
		
		logger.info("asas");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		
		String id =  errorManager.registerError(throwable, request, LastLinesAppender.getLog());
		
		id = id.replaceAll("\"", "");
		modelAndView.addObject("errorId",id);
		
		return modelAndView;
	}


}