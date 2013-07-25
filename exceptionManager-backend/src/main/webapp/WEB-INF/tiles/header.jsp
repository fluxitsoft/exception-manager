<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="isAuthenticated()">

	<script>
		var cometd;


		
		var addNotification = function(error) {

			

			var message = "<a href='<c:url value='/error?id='/>" + error.id + "'>" + error.targetExceptionClassName + "</a>";


			$.pnotify({
			    title: error.applicationName,
			    text: message,
			    type: 'error',
			    hide: true,
			    delay: 1500
			});

		}

		$(function() {

			var _connected = false;

			cometd = $.cometd;

			cometd.configure({
				url : 'http://localhost:8080/exceptionManager-backend/cometd',
				logLevel : 'info'
			});

			//cometd.websocketsEnabled = true;

			function connected() {

				cometd
						.subscribe(
								'/inbox/<security:authentication property="principal.username" />',
								function(m) {
									var response = $.parseJSON(m.data);

									$(document).trigger("error-received",
											response);

									addNotification(response);

									/*var id = response.id;
									var applicationName = response.applicationName;
									var exceptionClassName = response.targetExceptionClassName;
									var message = response.message;
									var date = response.time;

									var url = "<c:url value='/error?id=" + id + "'/>";
									$("#errors").prepend(
											'<tr><td><a href="' + url +'">' + id + '</a></td><td>'
													+ applicationName + '</td><td>' + exceptionClassName
													+ '</td><td>' + message
													+ '</td><td>' + date
													+ '</td></tr>');*/

								});

			}

			cometd.addListener('/meta/connect', function(message) {
				if (cometd.isDisconnected()) {
					return;
				}

				var wasConnected = _connected;
				_connected = message.successful;

				if (!wasConnected && _connected) {
					connected();
				} else if (wasConnected && !_connected) {
					// Disconnected
				}
			});

			cometd.addListener('/meta/disconnect', function(message) {
				if (message.successful) {
					_connected = false;
				}
			});

			cometd.handshake();

		});
	</script>
</security:authorize>


<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="#">Exception Manager</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href='<s:url value="/"></s:url>'>Home</a></li>
					<li><a href='<s:url value="/applications"></s:url>'>Applications</a></li>
					<li><a href='<s:url value="/errors"></s:url>'>Errors</a></li>
				</ul>
				<ul class="nav pull-right">
					<security:authorize access="!isAuthenticated()">
						<li><a href='<s:url value="/signin"></s:url>'>Sign in</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li><a href='<s:url value="/logout"></s:url>'>Logout (<security:authentication
									property="principal.username" />)
						</a></li>
					</security:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
