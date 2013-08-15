$(document).ready(function() {

	changeException();
	retrieveSourceCode();
	layoutTabs();
	layoutStackTrace();
	initialSelection();

});

layoutTabs = function() {

	$('#errorTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
}

initialSelection = function() {
	$(".exception-detail:first").trigger("click");
	$(".stackTraceElement:first").trigger("click");
	// $('a.toggles').trigger("click");
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
				// $("#exceptionDescriptorContainer-" + index).show();
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

									url : applicationSourceCodeUrl
											+ "?className="
											+ $(this).attr('classname')
											+ "&callback=?",
									async : false,
									contentType : "application/json",
									dataType : 'jsonp',

									success : function(data) {
										$("#sourceCode").html("");
										
										$("#sourceCode")
												.append(
														"<pre id='preSourceCode'></pre>");
										
										$("#sourceCode")
										.prepend(
												$(
														".stackTraceElement.label-info")
														.clone());

										// for ( var int = 0; int < 30; int++) {
										// data += "\n";
										// }
										// data += "."
										$("#preSourceCode").text(
												data.sourceCode);

										// SyntaxHighlighter.highlight();

										// $(".syntaxhighlighter").height($(window).height()
										// - 100);

										$("#preSourceCode").snippet("java", {
											style : "ide-eclipse",
											boxColor : "#5F9EA0",
											boxFill : "#5F9EA0",
											box : "" + lineNumber
										});

										$("#preSourceCode").css("height",
												"500px");
										$("#preSourceCode").css("margin-top",
										"10px");

										$("#preSourceCode")
												.animate(
														{
															scrollTop : $($("#preSourceCode > ol > li")[lineNumber - 1])
																	.offset().top - 300
														}, 0);
									},
									error : function(e) {
										console
												.log("Source code provider error: "
														+ e)
									}

								});

					});

}