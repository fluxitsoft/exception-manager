package ar.com.fluxit.errorManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fluxit.errorManager.model.ExceptionDescriptor;
import ar.com.fluxit.errorManager.service.ExceptionManagerService;


@Controller
class ErrorManagerController {

	@Autowired
	ExceptionManagerService exceptionManagerService;

	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code>
	 * element.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "updateExceptionDescriptor")
	@ResponseBody
	public String generalError(@RequestParam String className,
			@RequestParam String description) {

		ExceptionDescriptor exceptionDescriptor = new ExceptionDescriptor();
		exceptionDescriptor.setClassName(className);
		exceptionDescriptor.setDescription(description);

		exceptionManagerService.updateExceptionDescriptor(className, description);
		return "";

	}

}
