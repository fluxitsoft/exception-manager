Exception-manager [![](https://api.travis-ci.org/fluxitsoft/exception-manager.png?branch=master)](http://travis-ci.org/fluxitsoft/exception-manager) 
=====================

Aplicación para centralizar los errores de un conjunto de aplicaciones:
* Permite ver con detalle los errores de manera detallados lanzados por las aplicaciones en tiempo de ejecución. Permite ver el código fuente que falla, navegar por la pila de ejecución, conocer acerca del contexto de ejecución, etc..
* Permite recolectar información acerca de los errores de las aplicaciones y generar una base de conocimiento colaborativa.
* 
Desde acá podés ver algunos  [Screenshots](https://github.com/fluxitsoft/exception-manager/wiki/Screenshots)

**Instalación**

Instalar en la IDE: http://projectlombok.org/download.html
Instalar MongoDB - apt-get install mongodb


**Utilización**

1. Ingresar a la aplicación de exceptionManager y registrar una nueva aplicación.

2. Con ese id, ir a la aplicación que desea registrar errores y utilizar una instancia de ErrorManager para registrar errores.

* Agregar dependencias de Maven al proyecto.
```xml
		<dependency>
			<groupId>ar.com.fluxit</groupId>
			<artifactId>exceptionManager-client</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
		
		<dependency>
			<groupId>ar.com.fluxit</groupId>
			<artifactId>exceptionManager-sourceCodeProvider</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
```
* Agregar en algún punto de la aplicación que usted considera clave para detectar errores el registro de la excepción producida.

```java
ErrorManager errorManager = 
	new ErrorManagerImpl(applicationId, "http://localhost:8080/exceptionManager-backend/registerError");

...

errorManager.registerError(error);
```

* Agregar un Servlet en los casos que desea exponer el código fuente del proyecto. El único provider implementado hasta el momento es el que permite recolectar el código fuente desde el archivo de metadata de eclipse (.classpath).
```xml
     <servlet>
		<servlet-name>ExceptionManagerSourceCodeProvider</servlet-name>
        <servlet-class>
            ar.com.fluxit.em.servlet.EclipseSourceCodeProviderServlet
        </servlet-class>
        <init-param>
       		<param-name>M2_REPO</param-name>
        	<param-value>/home/xxx/.m2/repository</param-value>
        </init-param>
        <init-param>
       		<param-name>PROJECT_FOLDER</param-name>
        	<param-value>/home/xxx/workspace/project</param-value>
        </init-param>
	</servlet>

	<servlet-mapping>
        <servlet-name>ExceptionManagerSourceCodeProvider</servlet-name>
        <url-pattern>/em/sourceCode</url-pattern>
    </servlet-mapping>
```

**TODOs**

https://github.com/fluxitsoft/exception-manager/issues

