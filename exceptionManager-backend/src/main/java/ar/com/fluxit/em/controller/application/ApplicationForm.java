package ar.com.fluxit.em.controller.application;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import ar.com.fluxit.em.model.Application;

@Data
public class ApplicationForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

	@NotBlank(message = ApplicationForm.NOT_BLANK_MESSAGE)
	private String name;

	@NotBlank(message = ApplicationForm.NOT_BLANK_MESSAGE)
	private String sourceCodeServiceUrl;



	public Application getApplication() {
		Application application = new Application();
		application.setName(this.getName());
		application.setSourceCodeServiceUrl(this.getSourceCodeServiceUrl());
		return application;
	}
}
