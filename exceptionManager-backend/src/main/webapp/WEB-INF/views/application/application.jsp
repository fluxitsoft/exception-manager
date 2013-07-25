<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form" method="post" modelAttribute="application">
	<h2 class="form-heading">Application</h2>
	<form:errors path="" element="p" class="text-error" />
	<form:input path="name" class="input-block-level" placeholder="Name" />
	<form:errors path="name" element="p" class="text-error"/> 
	
	<form:input path="sourceCodeServiceUrl" class="input-block-level" placeholder="Source service url" /> 
	<form:errors path="sourceCodeServiceUrl" element="p" class="text-error"/> 
	
	<button class="btn btn-primary" type="submit">Add</button>
</form:form>
