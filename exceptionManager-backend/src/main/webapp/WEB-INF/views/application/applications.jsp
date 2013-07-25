<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>
<a class="btn btn-primary" href='<c:url value="/application"></c:url>'>Add application</a>
</p>
 <table class="table table-striped">
    <tr>
        <th>Name</th>
        <th>SourceCode Service Url</th>
        <th>Key</th>
    </tr>
    <c:forEach items="${applications}" var="application" varStatus="status">
        <tr>
            <td>${application.name}</td>
            <td>${application.sourceCodeServiceUrl}</td>
            <td>${application.key}</td>
        </tr>
    </c:forEach>
</table>  
