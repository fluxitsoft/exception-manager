package ar.com.fluxit.em.controller.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.fluxit.em.model.Application;
import ar.com.fluxit.em.service.ApplicationService;
import ar.com.fluxit.support.web.MessageHelper;

@Controller
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	
	@RequestMapping(value = "applications")
	public String list(Model model) {
		
		model.addAttribute("applications", applicationService.getApplications());
		
		return "applications";
	}

	@RequestMapping(value = "application")
	public String application(Model model) {
		
		model.addAttribute("application", new ApplicationForm());
		return "application";
	}

	@RequestMapping(value = "application", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute ApplicationForm applicationForm,
			Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return null;
		}

		Application application = applicationService.add(applicationForm
				.getApplication());

		MessageHelper.addSuccessAttribute(
				ra,
				"You have added a new application with key "
						+ application.getKey());

		return "redirect:/applications";
	}
}
