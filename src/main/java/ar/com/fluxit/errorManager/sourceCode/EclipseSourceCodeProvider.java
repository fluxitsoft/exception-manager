package ar.com.fluxit.errorManager.sourceCode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import lombok.Data;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Data
@Service
public class EclipseSourceCodeProvider implements SourceCodeProvider {

	@Value("${sourceProvider.eclipse.m2_repo}")
	private String m2_repo;

	@Value("${sourceProvider.eclipse.projectFolder}")
	private String projectFolder;

	private List<String> fileProviders = new ArrayList<String>();

	FileSystemManager fsManager;

	@Override
	public String getSourceCode(String className, String fileName) {

		String file = className.replaceAll("\\.", "/") + ".java";

		for (String fileProvider : fileProviders) {

			try {
				FileObject jarFile = fsManager.resolveFile(fileProvider);

				FileObject sourceCode = jarFile.resolveFile(file);
				InputStream inputStream = sourceCode.getContent()
						.getInputStream();
				return IOUtils.toString(inputStream);
			} catch (IOException e) {

			}
		}

		return "Source code not exist";
	}

	@PostConstruct
	public void initialize() throws Exception {

		fsManager = VFS.getManager();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(getProjectFolder()
				+ java.io.File.separator + ".classpath");
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath
				.compile("//classpath/classpathentry[@kind='var']");

		NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			Node attributeNode = node.getAttributes()
					.getNamedItem("sourcepath");
			if (attributeNode != null) {
				String path = attributeNode.getNodeValue();
				fileProviders.add("jar:"
						+ path.replaceAll("M2_REPO", getM2_repo()));
			}

		}

		xpath = xPathfactory.newXPath();
		expr = xpath.compile("//classpath/classpathentry[@kind='src']");

		list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			Node attributeNode = node.getAttributes().getNamedItem("path");
			if (attributeNode != null) {
				String path = attributeNode.getNodeValue();
				fileProviders.add("file:" + getProjectFolder() + java.io.File.separator + path);
			}

		}

	}

}
