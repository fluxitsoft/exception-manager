/*******************************************************************************
 * FluxIT 
 * Copyright (c) 2013 
 * Argentina
 * 
 * This is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version.
 * 
 * This is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package ar.com.fluxit.em.model;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class Error {
	
	
	private String applicationKey;
	
	private String sourceCodeUrlProvider;

	private Date time;

	private List<ExceptionDetail> exceptionDetails;

	private RequestContext requestContext;

	private Map<String, String> environmentProperties;

	private Map<String, String> systemProperties;
	
	private MemoryContext memoryContext = new MemoryContext();

	public String getApplicationKey() {
		return applicationKey;
	}

	public void setApplicationKey(String applicationKey) {
		this.applicationKey = applicationKey;
	}

	public String getSourceCodeUrlProvider() {
		return sourceCodeUrlProvider;
	}

	public void setSourceCodeUrlProvider(String sourceCodeUrlProvider) {
		this.sourceCodeUrlProvider = sourceCodeUrlProvider;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<ExceptionDetail> getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(List<ExceptionDetail> exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

	public Map<String, String> getEnvironmentProperties() {
		return environmentProperties;
	}

	public void setEnvironmentProperties(Map<String, String> environmentProperties) {
		this.environmentProperties = environmentProperties;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public MemoryContext getMemoryContext() {
		return memoryContext;
	}

	public void setMemoryContext(MemoryContext memoryContext) {
		this.memoryContext = memoryContext;
	}

}
