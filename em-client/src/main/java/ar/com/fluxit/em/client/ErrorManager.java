package ar.com.fluxit.em.client;

import javax.servlet.http.HttpServletRequest;

public interface ErrorManager {

	String registerError(Throwable throwable);

	String registerError(Throwable throwable,
			HttpServletRequest httpServletRequest);

}
