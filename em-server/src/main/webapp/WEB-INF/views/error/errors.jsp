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
		var exceptionClassName = params.targetExceptionShortClassName;
		var message = params.message;
		var date = params.time;

		var url = "<c:url value='/error?id=' />" + id;
		
		
		$("#errors").prepend(
				'<tr><td><a class="btn btn-mini" href="' + url +'"><i class="icon-eye-open"></i></a></td><td>'
						+ applicationName + '</td><td>' + exceptionClassName
						+ '</td><td>' + message
						+ '</td><td>' + date
						+ '</td></tr>');



	});
	$("#application").change(function(selected){
		document.location.href = "<c:url value='/errors?applicationKey=' />" +  $('#application option:selected').val();
	});
});

</script>

Application: <select id='application' class="form-control">
 	
    <option value="${applicationSelected.key}" selected>${applicationSelected.name}</option>
    <c:forEach items="${applications}" var="application">
        <c:if test="${application.key != applicationSelected.key}">
            <option value="${application.key}">${application.name}</option>
        </c:if>
    </c:forEach>
</select>

<table class="table  table-striped" id="errors">

    <c:forEach items="${errors}" var="error" varStatus="status">
        
        <tr>
            <td><a class="btn btn-mini" href="<c:url value='/error?id=${error.id}'/>"><i class="icon-eye-open"></i></a></td>
            <td>${error.applicationName}</td>
            <td>${error.targetExceptionShortClassName}</td>
            <td>${error.message}</td>
            <td width="250px">${error.time}</td>
            
        </tr>
    </c:forEach>

</table>
