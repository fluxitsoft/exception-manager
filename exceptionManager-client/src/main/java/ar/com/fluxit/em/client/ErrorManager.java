package ar.com.fluxit.em.client;

import javax.servlet.http.HttpServletRequest;

public interface ErrorManager {

	void registerError(Throwable throwable);

	void registerError(Throwable throwable,
			HttpServletRequest httpServletRequest);

}
