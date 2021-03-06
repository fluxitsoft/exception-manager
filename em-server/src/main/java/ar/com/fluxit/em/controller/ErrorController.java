package ar.com.fluxit.em.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.fluxit.em.model.Application;
import ar.com.fluxit.em.model.Error;
import ar.com.fluxit.em.service.ApplicationService;
import ar.com.fluxit.em.service.ErrorIndexService;
import ar.com.fluxit.em.service.ErrorService;
import ar.com.fluxit.em.solr.document.ErrorDocument;


@Controller
class ErrorController {

	@Autowired
	ErrorService errorService;
	
	@Autowired
	ErrorIndexService errorIndexService;
	
	@Autowired
	ApplicationService applicationService;
	
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code>
	 * element.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "registerError")
	@ResponseBody
	public String registerError(@RequestBody ar.com.fluxit.em.model.ErrorDocument error) {
		
		errorService.addError(error);
		
		return error.getId();

	}
	

	@RequestMapping(method = RequestMethod.GET, value = "error/find")
	@ResponseBody
	public List<ErrorDocument> registerError(@RequestParam("q") String searchTerm) {
		
		return errorIndexService.find(searchTerm);
		
	}
	
	@RequestMapping(value = "error")
	public String error(@RequestParam String id, Model model) {

		Error error = errorService.getError(id);
		
		model.addAttribute("error",error);
		
		return "error";
	}
	
	
	@RequestMapping(value = "simpleError")
	public String simpleError(@RequestParam String id, Model model) {

		error(id, model);
		
		return "simpleError";
	}
	
	
	@RequestMapping(value = "errors")
	public String errors(Model model, @RequestParam(required=false) String applicationKey) {
		Application selected = null;
		
		Application allApplication = new Application();
		allApplication.setName("All");
		
		if(StringUtils.isNotEmpty(applicationKey)){
			selected = applicationService.getApplication(applicationKey);
		}else{
			selected = allApplication;
		}
		
		List<Application> applications = applicationService.getApplications();
		
		applications.add(0, allApplication);
		
		List<ErrorDetail> errors = errorService.getErrorDetails(applicationKey);
		model.addAttribute("applications",applications);
		model.addAttribute("errors",errors);
		model.addAttribute("applicationSelected",selected);
		
		return "errors";
	}
	

}
