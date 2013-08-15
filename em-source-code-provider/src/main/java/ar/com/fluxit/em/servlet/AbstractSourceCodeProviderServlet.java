package ar.com.fluxit.em.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractSourceCodeProviderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String className = request.getParameter("className");
		String fileName = request.getParameter("fileName");
		String callback = request.getParameter("callback");

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("sourceCode",
					this.getSourceCode(className, fileName));
		} catch (JSONException e) {
			throw new ServletException(e);
		}

		String sourceCodeResponse = jsonObject.toString();
		if (callback != null) {
			sourceCodeResponse = callback + "(" + sourceCodeResponse + ");";
		}

		PrintWriter out = response.getWriter();
		out.append(sourceCodeResponse);
		out.close();
	}

	protected abstract String getSourceCode(String className, String fileName);

}
