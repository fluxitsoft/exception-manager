package ar.com.fluxit.em.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestContext {

	private String contextName;

	private Map<String, String> parameters;

	private Map<String, String> headers;

	private List<String> cookies;

	private String method;

	private String queryString;

}
