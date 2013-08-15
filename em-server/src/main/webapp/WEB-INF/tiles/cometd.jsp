<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="isAuthenticated()">

	<script>
		var cometd;

		$(function() {

			var _connected = false;

			var addNotification = function(error) {

				var message = "<a href='<c:url value='/error?id='/>" + error.id
						+ "'>" + error.targetExceptionClassName + "</a>";

				$.pnotify({
					title : error.applicationName,
					text : message,
					type : 'error',
					hide : true,
					delay : 10000,
					width: "500px"
				});

			}
			cometd = $.cometd;

			cometd.configure({
				url : "<c:url value='/cometd'/>",
				logLevel : "info"
			});

			//cometd.websocketsEnabled = true;

			function connected() {

				cometd
						.subscribe(
								'/inbox',
								function(m) {
									var response = $.parseJSON(m.data);

									$(document).trigger("error-received",
											response);

									addNotification(response);

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

