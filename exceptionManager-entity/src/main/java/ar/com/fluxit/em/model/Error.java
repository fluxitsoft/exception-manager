package ar.com.fluxit.em.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Error {
	
	
	private String applicationKey;
	
	private String sourceCodeUrlProvider;

	private Date time;

	private List<ExceptionDetail> exceptionDetails;

	private RequestContext requestContext;

	private Map<String, String> environmentProperties;

	private Map<String, String> systemProperties;
	
	private MemoryContext memoryContext = new MemoryContext();

}
