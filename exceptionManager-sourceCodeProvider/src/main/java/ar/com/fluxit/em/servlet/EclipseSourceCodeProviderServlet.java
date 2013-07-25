package ar.com.fluxit.em.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import ar.com.fluxit.em.sourceCode.EclipseSourceCodeProvider;

public class EclipseSourceCodeProviderServlet extends
		AbstractSourceCodeProviderServlet {
	

	private static final long serialVersionUID = 1L;
	
	
	private EclipseSourceCodeProvider sourceCodeProvider;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		sourceCodeProvider = new EclipseSourceCodeProvider();
		sourceCodeProvider.setM2_repo(config.getInitParameter("M2_REPO"));
		sourceCodeProvider.setProjectFolder(config.getInitParameter("PROJECT_FOLDER"));
		try {
			sourceCodeProvider.initialize();
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}
	
	
	@Override
	protected String getSourceCode(String className, String fileName) {
		
		return sourceCodeProvider.getSourceCode(className, fileName);
	}

}
