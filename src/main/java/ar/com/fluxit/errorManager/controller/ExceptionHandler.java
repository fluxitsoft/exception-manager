package ar.com.fluxit.errorManager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.fluxit.errorManager.service.ExceptionManagerService;

@ControllerAdvice
@Controller
class ExceptionHandler {

	@Autowired
	ExceptionManagerService exceptionManager;

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
	public ModelAndView exception(Throwable throwable,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		modelAndView.addObject("exceptionDetails",
				exceptionManager.fillExceptionDetails(throwable));
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			statusCode = 500;
		}
		return modelAndView;
	}

	@RequestMapping("generalError")
	public ModelAndView generalError(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");

		return exception(throwable, request);

	}
}