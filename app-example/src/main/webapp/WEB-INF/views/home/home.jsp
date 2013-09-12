<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
	<h1>
		<s:message code="view.index.title" />
	</h1>
	<p>Welcome to the Exception Manager - APP Example!</p>
	<p>
		<a href="<s:url value='/errorRequest' />"
			class="btn btn-primary btn-large"> Simple request error </a> <a
			id="btn" class="btn btn-primary btn-large"> Ajax request error </a>
	</p>

	<script type="text/javascript">
		$("#btn").click(function() {
			$.ajax({
				type : "GET",
				url : "<s:url value='/errorRequest' />",
				data : {
					name : "John",
					location : "Boston"
				},
				success : function(data) {
					console.log("Success: " + data);
				},
				error : function(data) {
					console.log("Error: " + data);
				}
			});
		});
	</script>
</div>