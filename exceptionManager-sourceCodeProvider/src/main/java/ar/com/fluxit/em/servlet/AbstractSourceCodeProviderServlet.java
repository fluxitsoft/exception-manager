package ar.com.fluxit.em.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class  AbstractSourceCodeProviderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String className = request.getParameter("className");
		String fileName = request.getParameter("fileName");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		
		String sourceCode = this.getSourceCode(className, fileName);

		PrintWriter out = response.getWriter();
		out.append(sourceCode);
		out.close();
	}

	protected abstract String getSourceCode(String className, String fileName);
	
	
	

}
