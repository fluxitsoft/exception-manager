package ar.com.fluxit.em.solr.document;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import ar.com.fluxit.em.model.ExceptionDetail;

public class ErrorDocument {

	@Id
	@Field
	private String id;

	@Field(value="exceptionClassName")
	private String exceptionClassName;

	@Field
	private String exceptionMessage;


	// @Field
	// private String application;

	public ErrorDocument() {
	}

	public ErrorDocument(ar.com.fluxit.em.model.ErrorDocument error) {

		this.id = error.getId();

		ExceptionDetail exceptionDetail = error.getExceptionDetails().get(0);

		this.exceptionClassName = exceptionDetail.getClassName();
		this.exceptionMessage = exceptionDetail.getMessage();

		// this.application = error.getApplicationKey();
//		this.stacktrace = this.stacktraceToString(exceptionDetail
//				.getStackTraceElements());
	}

	private String stacktraceToString(List<StackTraceElement> stackTraceElements) {
		StringBuffer stringBuffer = new StringBuffer();
		int i = 0;
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			stringBuffer.append(stackTraceElement.toString() + "\n");
			if (i == 4) {
				break;
			}
		}
		return stringBuffer.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExceptionClassName() {
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

//	public String getStacktrace() {
//		return stacktrace;
//	}
//
//	public void setStacktrace(String stacktrace) {
//		this.stacktrace = stacktrace;
//	}

}
