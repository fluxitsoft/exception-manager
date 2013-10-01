<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<script type="text/javascript">
	var applicationSourceCodeUrl = "<c:out value='${error.sourceCodeUrlProvider}'/>";
</script>


<link href="<c:url value="/resources/css/core.css" />" rel="stylesheet"
	media="screen" />

<script type="text/javascript"
	src="<c:url value='/resources/js/jquery.snippet.min.js'/>"></script>

<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/jquery.snippet.min.css'/>" />


<!-- 


<script type="text/javascript"
	src="<c:url value='/resources/js/sh/shCore.js'/>"></script>

<script type="text/javascript"
	src="<c:url value='/resources/js/sh/shBrushJava.js'/>"></script>


<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/sh/shCore.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/sh/shCoreEclipse.css'/>" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.scrollTo-1.4.3.1-min.js" />"></script>
-->

<script type="text/javascript"
	src="<c:url value="/resources/js/error.js" />"></script>
</head>


<style>
.container {
	width: 95%;
}
</style>

<!-- <a id="toggle-update" href="#" class="toggles"><i -->
<!-- 	class="icon-chevron-down"></i></a> -->

<ul class="nav nav-tabs" id="errorTab">
	<li class="active"><a href="#stacktrace">Stacktrace</a></li>
	<li><a href="#request">Request context</a></li>
	<li><a href="#variables">Variables context</a></li>
	<li><a href="#memory">Memory context</a></li>
	<li><a href="#log">Log</a></li>
	<li><a href="#knowledge">Knowledge</a></li>
	<li><a href="#others">Others</a></li>
</ul>


<div class="tab-content">

	<div class="tab-pane active" id="stacktrace">

		<ul class="nav nav-pills">
			<c:forEach items="${error.exceptionDetails}" var="exceptionDetail">
				<li class="exception-detail"><a href="#">${exceptionDetail.className}</a></li>
			</c:forEach>
		</ul>


		<c:forEach items="${error.exceptionDetails}" var="exceptionDetail"
			varStatus="status">
			<span id="exceptionMessage-${status.count - 1}"
				class="label label-warning">${exceptionDetail.message}</span>
		</c:forEach>

		<br /> <br />
		<div class="container-fluid">
			<div class="row-fluid">

				<div id="sidebar" class="span6">
					<c:forEach items="${error.exceptionDetails}" var="exceptionDetail"
						varStatus="status">
						<div id="stacktrace-${status.count - 1}">
							<c:forEach items="${exceptionDetail.stackTraceElements}"
								var="stackTraceElement">
								<span class="label stackTraceElement"
									classname="${stackTraceElement.className}"
									linenumber="${stackTraceElement.lineNumber}">
									${stackTraceElement.className}.${stackTraceElement.methodName}:
									${stackTraceElement.lineNumber}</span>
								<br />
							</c:forEach>
						</div>
					</c:forEach>

				</div>
				<div id="content" class="span6" style="overflow: none;">
					<div id="sourceCode" style="width: 100%;"></div>
					<a id="toggle-stack" href="#" class="toggles"><i
						class="icon-chevron-left"></i></a>
				</div>
			</div>

		</div>

	</div>
	<div class="tab-pane" id="request">

		<h3>Headers:</h3>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Value</th>

			</tr>
			<c:forEach items="${error.requestContext.headers}" var="headerEntry">
				<tr>
					<td>${headerEntry.key}</td>
					<td>${headerEntry.value}</td>
				</tr>
			</c:forEach>
		</table>

		<h3>Parameters:</h3>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Value</th>

			</tr>
			<c:forEach items="${error.requestContext.parameters}" var="parameter">
				<tr>
					<td>${parameter.key}</td>
					<td>${parameter.value}</td>
				</tr>
			</c:forEach>
		</table>

		<h3>Cookies:</h3>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Domain</th>
				<th>Secure</th>

			</tr>
			
			<c:forEach items="${error.requestContext.cookies}" var="cookieEntry">
				<tr>
					<td>${cookieEntry.name}</td>
					<td>${cookieEntry.value}</td>
					<td>${cookieEntry.secure}</td>
				</tr>
			</c:forEach>
		</table>
		

		<h3>Others</h3>
		<table class="table table-striped">
			<tr>
				<td>Method</td>
				<td>${error.requestContext.method}</td>
			</tr>

			<tr>
				<td>Query string</td>
				<td>${error.requestContext.queryString}</td>
			</tr>

			<tr>
				<td>Request Session Id</td>
				<td>${error.requestContext.requestSessionId}</td>
			</tr>

			<tr>
				<td>Context path</td>
				<td>${error.requestContext.contextPath}</td>
			</tr>

			<tr>
				<td>Context name</td>
				<td>${error.requestContext.contextType}</td>
			</tr>
		</table>

	</div>

	<div class="tab-pane" id="variables">


		<h3>Environment variables:</h3>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Value</th>
			</tr>
			<c:forEach items="${error.environmentProperties}" var="variable">
				<tr>
					<td>${variable.key}</td>
					<td>${variable.value}</td>
				</tr>
			</c:forEach>
		</table>

		<h3>System properties:</h3>
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Value</th>
			</tr>
			<c:forEach items="${error.systemProperties}" var="variable">
				<tr>
					<td>${variable.key}</td>
					<td>${variable.value}</td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<div class="tab-pane" id="memory">
		<h3>Heap:</h3>
		<table class="table table-striped">
			<tr>
				<td>Init</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.heapInit / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Commited</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.heapCommitted / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Used</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.heapUsed / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Max</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.heapMax / 1024}"></fmt:formatNumber>KB</td>
			</tr>
		</table>

		<h3>Non Heap:</h3>
		<table class="table table-striped">
			<tr>
				<td>Init</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.nonHeapInit / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Commited</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.nonHeapCommitted / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Used</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.nonHeapUsed / 1024}"></fmt:formatNumber>KB</td>
			</tr>

			<tr>
				<td>Max</td>
				<td><fmt:formatNumber maxFractionDigits="3"
						value="${error.memoryContext.nonHeapMax / 1024}"></fmt:formatNumber>KB</td>
			</tr>
		</table>

	</div>
	
	
	<div class="tab-pane" id="log">
		<pre>${error.log}</pre>
	</div>
	
	<div class="tab-pane" id="knowledge">
		
		
		<div class="container-fluid">
	<div class="row-fluid">
		<div class="span1">
			<div class="btn-group-vertical">
				<button class="btn" type="button"><em class="icon-edit"></em></button> <button class="btn" type="button"><em class="icon-thumbs-down"></em></button> <button class="btn" type="button"><em class="icon-thumbs-up"></em></button> <button class="btn" type="button"><em class="icon-star"></em></button> <button class="btn" type="button"><em class=" icon-signal"></em></button>
			</div>
		</div>
		<div class="span11">
			<p>When checking the database, the table person <strong>does</strong> exist! The connection details to the database (which can be found in the <code>mapping.cfg.xml</code> file) are also correct.</p>
<h4>Diagnosis</h2>

<p>First, check to see if the problem is cases sensitivity. Connect to the database using your sql client. Run the queries:</p>
<div class="code panel pdl" style="border-width: 1px;"><div class="codeContent panelContent pdl">
<pre>
select * from PERSON;
</pre>
</div></div>

<p>And:</p>

<div class="code panel pdl" style="border-width: 1px;"><div class="codeContent panelContent pdl">
<pre>
select * from person;
</pre>
</div></div>

<h4>Cause</h4>
<p>The database is case sensitive, or the person table is not created as described in <a href="http://jira.atlassian.com/browse/BUG-9959" class="external-link" rel="nofollow">BUG-9959</a>.</p>

<h4>Resolution</h4>
<div><div>
<p>See also <a href="/display/CONFKB/Cannot+Restore+XML+Backup+Due+to+Database+Permissions">Cannot Restore XML Backup Due to Database Permissions</a>.</p>
</div></div>
		</div>
	</div>
	<hr/>
	<div class="row-fluid">
		<div class="span6">
			<h3>
				JavaDoc
			</h3>
			<p>
				    <P>An exception that provides information on a database access
error or other errors.

<P>Each <code>SQLException</code> provides several kinds of information:
<UL>
  <LI> a string describing the error.  This is used as the Java Exception
      message, available via the method <code>getMesasge</code>.
  <LI> a "SQLstate" string, which follows either the XOPEN SQLstate conventions
       or the SQL:2003 conventions.
      The values of the SQLState string are described in the appropriate spec.
      The <code>DatabaseMetaData</code> method <code>getSQLStateType</code>
      can be used to discover whether the driver returns the XOPEN type or
      the SQL:2003 type.
  <LI> an integer error code that is specific to each vendor.  Normally this will
      be the actual error code returned by the underlying database.
  <LI> a chain to a next Exception.  This can be used to provide additional
      error information.
  <LI> the causal relationship, if any for this <code>SQLException</code>.
</UL>
	
		</div>
		<div class="span6">
			<h3>
				Similar errors
			</h3>
			<table class="table table-tripped">
				<thead>
					<tr>
	
						<th>
							Application
						</th>
						<th>
							Percent
						</th>
						<th>
							Date
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							Application X
						</td>
						<td>%98
						</td>
						<td>
							01/04/2012
						</td>
					</tr>
					<tr>
						<td>
							Application Y
						</td>
						<td>
							%95
						</td>
						<td>
							01/04/2013
						</td>
						
					</tr>
					<tr>
						<td>
							Application Z
						</td>
						<td>
							%70
						</td>
						<td>
							01/04/2013
						</td>
					</tr>
					<tr>
						<td>
							Application Y
						</td>
						<td>
							%59
						</td>
						<td>
							03/04/2012
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
		
		
	</div>
	
	
	
	<div class="tab-pane" id="others">
		<table class="table table-striped">
			<tr>
				<td>Time</td>
				<td>${error.time}</td>
			</tr>

			<tr>
				<td>Source code provider</td>
				<td>${error.sourceCodeUrlProvider}</td>
			</tr>

		</table>

	</div>
	
	
</div>

