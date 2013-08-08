<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Exception</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	var contextUrl = '<c:url value="/"/>';
</script>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/core.css" />" rel="stylesheet"
	media="screen" />

<tilesx:useAttribute id="styles" name="styles"
	classname="java.util.List" ignore="true" />
<c:forEach var="cssName" items="${styles}">
	<link type="text/css" href="<c:url value="/resources/css/${cssName}"/>"
		rel="stylesheet" media="screen" />
</c:forEach>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->


<script type="text/javascript"
	src="<c:url value='/resources/js/sh/shCore.js'/>"></script>
<!--  <script type="text/javascript"
	src="<c:url value='/resources/js/sh/shLegacy.js'/>"></script>
-->
<script type="text/javascript"
	src="<c:url value='/resources/js/sh/shBrushJava.js'/>"></script>


<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/sh/shCore.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/sh/shCoreEclipse.css'/>" />

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.scrollTo-1.4.3.1-min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/generalError.js" />"></script>
</head>
<body class="error">
	<a id="toggle-update" href="#" class="toggles"><i
		class="icon-chevron-down"></i></a>
	<ul class="nav nav-pills">
		<c:forEach items="${exceptionDetails}" var="exceptionDetail">
			<li class="exception-detail"><a href="#">${exceptionDetail.className}</a></li>
		</c:forEach>
	</ul>


	<c:forEach items="${exceptionDetails}" var="exceptionDetail"
		varStatus="status">
		<span id="exceptionMessage-${status.count - 1}"
			class="label label-warning">${exceptionDetail.message}</span>
	</c:forEach>
	</br>
	</br>
	<c:forEach items="${exceptionDetails}" var="exceptionDetail"
		varStatus="status">
		<div id="exceptionDescriptorContainer-${status.count - 1}"
			style="width: 100%;">
			<input type="hidden" id="id"
				value="${exceptionDetail.exceptionDescriptor.id}" /> <input
				type="hidden" id="className"
				value="${exceptionDetail.exceptionDescriptor.className}" />
			<textarea id="description" style="width: 100%">${exceptionDetail.exceptionDescriptor.description}</textarea>
			<br /> <input type="button"
				class="btn btn-primary exceptionDescriptor-update" value="Update" />
			<br />
			<hr />
		</div>
	</c:forEach>

	<div class="container-fluid">
		<div class="row-fluid">

			<div id="sidebar" class="span6">
				<c:forEach items="${exceptionDetails}" var="exceptionDetail"
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
				<div id="sourceCode"></div>
				<a id="toggle-stack" href="#" class="toggles"><i
					class="icon-chevron-left"></i></a>
			</div>
		</div>
	</div>
</body>
</html>


