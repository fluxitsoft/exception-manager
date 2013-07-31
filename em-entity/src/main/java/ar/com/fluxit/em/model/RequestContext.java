package ar.com.fluxit.em.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestContext {

	private String contextType;

	private Map<String, String> parameters;

	private Map<String, String> headers = new HashMap<>();

//	private List<Cookie> cookies;

	private String method;

	private String queryString;
	
	private String requestSessionId;
	
	private String contextPath;
	


}
