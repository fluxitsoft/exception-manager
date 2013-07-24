web-exception-manager
=====================

Plugin para visualización de excepciones y errores en aplicaciones JAVA like Grails, con detalle de líneas de código y visualización de código fuente

** Instalación **

Instalar en la IDE: http://projectlombok.org/download.html
Modificar el archivo application.properties indicando la ubicación del proyecto y la ubicación del repositorio de Maven.


**TODOs**

* Buscar el código fuente por el nombre del fileName que devuelve el StackTrace.
* Mostrar el código de error HTTP.
* Manejear las innerClasses.
* Arreglear el widget de highlight porque parsea mal el código java y se ve como comentario.
* Aislar la solución dentro de un Jar que no to tenga dependencias con alguna tecnología específica.
* Los errores 404 estan entrando en ciclo ;).
* Etc, etc..
