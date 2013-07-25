package ar.com.fluxit.em.model;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionDetail {

	private String className;
	
	private String message;

	private List<StackTraceElement> stackTraceElements;

}
