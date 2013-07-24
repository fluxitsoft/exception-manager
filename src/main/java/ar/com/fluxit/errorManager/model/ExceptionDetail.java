package ar.com.fluxit.errorManager.model;

import java.util.List;


import lombok.Data;

@Data
public class ExceptionDetail {

	private String className;
	
	private String message;

	private ExceptionDescriptor exceptionDescriptor;

	private List<StackTraceElement> stackTraceElements;

}
