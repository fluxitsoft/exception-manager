$(document).ready(function() {

	changeException();
	retrieveSourceCode();
	layoutStackTrace();
	initialSelection();
	updateExceptionDescriptor();

});

updateExceptionDescriptor = function() {

//	$(".exceptionDescriptor-update").click(function() {
//
//		$.ajax({
//			type : "POST",
//			url : contextUrl + "updateExceptionDescriptor",
//			data : {
//				id : $(this).parent().find("#id").val(),
//				description : $(this).parent().find("#description").val(),
//				className : $(this).parent().find("#className").val()
//
//			},
//			success : function(data) {
//			}
//
//		});
//
//	});

}

initialSelection = function() {
	$(".exception-detail:first").trigger("click");
	$(".stackTraceElement:first").trigger("click");
//	$('a.toggles').trigger("click");
}

layoutStackTrace = function() {
	$('#toggle-stack').click(
			function() {
				$('#toggle-stack i').toggleClass(
						'icon-chevron-left icon-chevron-right');

				$('#sidebar').animate({
					width : 'toggle'
				}, 100);
				$('#content').toggleClass('span12 span6');
			});

	$('#toggle-update').click(
			function() {
				$('#toggle-update i').toggleClass(
						'icon-chevron-down icon-chevron-up');

				var index = $(".exception-detail").index(
						$(".exception-detail.active"));
				$("#exceptionDescriptorContainer-" + index).animate({
					width : 'toggle'
				}, 0);
			});
}

changeException = function() {
	$(".exception-detail").click(
			function() {
				$(".exception-detail.active").removeClass("active");
				$(this).addClass("active");
				var index = $(".exception-detail").index(this);
				$("[id^=exceptionDescriptorContainer-]").hide();
				$("[id^=exceptionMessage-]").hide();
				$("#exceptionMessage-" + index).show();
//				$("#exceptionDescriptorContainer-" + index).show();
				$("[id^=stacktrace-]").hide();
				$("#stacktrace-" + index).show();
				$("#stacktrace-" + index).find(".stackTraceElement:first")
						.trigger("click");

			});
}

retrieveSourceCode = function() {

	$(".stackTraceElement")
			.click(
					function() {

						var lineNumber = $(this).attr('linenumber');
						$(".stackTraceElement").removeClass("label-info");
						$(this).addClass("label-info");
						$
								.ajax({
									type : "GET",
									url : applicationSourceCodeUrl,
									data : {
										className : $(this).attr('classname'),
										fileName : "null"

									},
									success : function(data) {
										$("#sourceCode").html("");
										$("#sourceCode").append($(".stackTraceElement.label-info").clone());
										$("#sourceCode")
												.append(
														"<pre id='preSourceCode' style='height: 500px;' class='brush: java; highlight: ["
																+ lineNumber
																+ "];' ></pre>");

										for ( var int = 0; int < 30; int++) {
											data += "\n";
										}
										data += "."
										$("#preSourceCode").text(data);

										SyntaxHighlighter.highlight();

										$(".syntaxhighlighter").height($(window).height() - 100);
										
										$(".syntaxhighlighter").animate(
												{
													scrollTop : $(
															".highlighted")
															.offset().top - 241
												}, 0);
									}
								});

					});

}