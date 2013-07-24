import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

public class VfsTest {

	public static void main(String[] args) {
		try {

			FileSystemManager fsManager = VFS.getManager();
			
			long start = new Date().getTime();
			FileObject jarFile = fsManager
					.resolveFile("jar:/home/anselmo/.m2/repository/org/springframework/spring-context/3.2.2.RELEASE/spring-context-3.2.2.RELEASE-sources.jar");

			FileObject sourceCode = jarFile
					.resolveFile("org/springframework/context/ApplicationContext.java");
			InputStream inputStream = sourceCode.getContent().getInputStream();

			System.out.println(IOUtils.toString(inputStream));
			// List the children of the Jar file
			
			long finish = new Date().getTime();
			System.out.println(finish - start);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
