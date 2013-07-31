package ar.com.fluxit.em.controller;

import lombok.Data;

@Data
public class ErrorDetail {

	private String id;
	private String applicationName;
	private String targetExceptionClassName;
	private String message;
	private String time;


}
