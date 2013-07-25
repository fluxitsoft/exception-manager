<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<script type="text/javascript">

$(document).ready(function(){

	$(document).bind("error-received", function(event, params) {
		console.log(event);
		console.log(params);

		var id = params.id;
		var applicationName = params.applicationName;
		var exceptionClassName = params.targetExceptionClassName;
		var message = params.message;
		var date = params.time;

		var url = "<c:url value='/error?id=" + id + "'/>";
		$("#errors").prepend(
				'<tr><td><a href="' + url +'">' + id + '</a></td><td>'
						+ applicationName + '</td><td>' + exceptionClassName
						+ '</td><td>' + message
						+ '</td><td>' + date
						+ '</td></tr>');
	})
});

</script>

<table class="table" id="errors">

    <c:forEach items="${errors}" var="error" varStatus="status">
        <tr>
        
            <td><a href="<c:url value='/error?id=${error.id}'/>">${error.id}</a></td>
            <td>${error.applicationName}</td>
            <td>${error.targetExceptionClassName}</td>
            <td>${error.message}</td>
            <td>${error.time}</td>
            
        </tr>
    </c:forEach>

</table>