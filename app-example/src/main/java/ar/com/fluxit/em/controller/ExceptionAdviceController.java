package ar.com.fluxit.em.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.com.fluxit.em.client.ErrorManagerImpl;


@Controller
@ControllerAdvice
class ExceptionAdviceController {

	private ErrorManagerImpl errorManager;

	public ExceptionAdviceController() {
		 errorManager = new ErrorManagerImpl("ef79164d-d8c9-4d3e-b7b5-b1a010094d95", "http://localhost:8080/em-server/registerError");
	}
	
	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView exception(Throwable throwable,
			HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		
		String id =  errorManager.registerError(throwable, request);
		id = id.replaceAll("\"", "");
		modelAndView.addObject("errorId",id);

		return modelAndView;
	}


}