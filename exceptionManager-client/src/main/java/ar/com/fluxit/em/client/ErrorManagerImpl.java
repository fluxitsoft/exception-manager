package ar.com.fluxit.em.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
import ar.com.fluxit.em.model.Error;
import ar.com.fluxit.em.model.ExceptionDetail;
import ar.com.fluxit.em.model.RequestContext;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
@Data
public class ErrorManagerImpl implements ErrorManager {

	private String applicationKey;

	private String errorManagerUrl;
	
	
	public ErrorManagerImpl(String applicationKey, String errorManagerUrl) {
		super();
		this.applicationKey = applicationKey;
		this.errorManagerUrl = errorManagerUrl;
	}

	@Override
	public void registerError(Throwable throwable) {
		registerError(throwable, null);

	}

	@Override
	public void registerError(Throwable throwable, HttpServletRequest request) {

		Error error = new Error();
		
		error.setApplicationKey(applicationKey);

		this.fillRequestContext(request, error);

		error.setEnvironmentProperties(System.getenv());
		error.setSystemProperties(new HashMap(System.getProperties()));

		error.setExceptionDetails(fillExceptionDetails(throwable));

		this.sendError(error);

	}

	private void sendError(Error error) {
		try {

			Gson gson = new Gson();
			String json = gson.toJson(error);

			URL url = new URL(errorManagerUrl);

			URLConnection urlc = url.openConnection();

			urlc.setDoInput(true);
			urlc.setDoOutput(true);
			urlc.setRequestProperty("Accept", "application/json");
			urlc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        OutputStream os = urlc.getOutputStream();
	        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
	        writer.write(json);
	        writer.close();
	        os.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String l = null;
			while ((l = br.readLine()) != null) {
				System.out.println(l);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fillRequestContext(HttpServletRequest request, Error error) {
		if (request != null) {
			RequestContext requestContext = new RequestContext();
			requestContext.setQueryString(request.getQueryString());
			requestContext.setMethod(request.getMethod());
			requestContext.setParameters(request.getParameterMap());

			error.setRequestContext(requestContext);
		}
	}

	private List<ExceptionDetail> fillExceptionDetails(Throwable throwable) {
		List<ExceptionDetail> result = new ArrayList<ExceptionDetail>();
		List<Throwable> throwables = Throwables.getCausalChain(throwable);

		for (Throwable throwableCause : throwables) {
			ExceptionDetail exceptionDetail = new ExceptionDetail();

			exceptionDetail.setClassName(throwableCause.getClass().getName());

			exceptionDetail.setMessage(throwableCause.getMessage());

			exceptionDetail.setStackTraceElements(Arrays.asList(throwableCause
					.getStackTrace()));
			result.add(0, exceptionDetail);
		}
		return result;
	}

}
