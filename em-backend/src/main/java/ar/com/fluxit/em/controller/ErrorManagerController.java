package ar.com.fluxit.em.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fluxit.em.model.Error;
import ar.com.fluxit.em.service.ExceptionManagerService;

import com.google.gson.Gson;


@Controller
class ErrorManagerController {

	@Autowired
	ExceptionManagerService exceptionManagerService;
	
	
	private Gson gson;
	
	public ErrorManagerController() {
		gson = new Gson();
	}
	

	
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code>
	 * element.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "registerError")
	@ResponseBody
	public String registerError(@RequestBody ar.com.fluxit.em.model.ErrorDocument error) {
		
		exceptionManagerService.addError(error);
		
		return error.getId();

	}
	
	@RequestMapping(value = "error")
	public String error(@RequestParam String id, Model model) {

		Error error = exceptionManagerService.getError(id);
		
		model.addAttribute("error",error);
		
		return "error";
	}
	
	
	@RequestMapping(value = "errors")
	public String errors(Model model) {

		List<ErrorDetail> errors = exceptionManagerService.getErrorDetails();
		
		
		model.addAttribute("errors",errors);
		
		return "errors";
	}
	

}
