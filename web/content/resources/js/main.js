(function($) {
	
	$(document).ready(function() {
		
		var message = document.getElementById("message").innerHTML.trim();
		if(message != null && message != "") {
			window.setTimeout(function() {
				$("#message").show('slow');
			}, 0);
			window.setTimeout(function() {
				$("#message").hide('slow');
			}, 3000);
		}
		
		$("[data-menu]").each(function(i, elem) {
			$(elem).click(function() {
				var uri = $(elem).attr('id');
				window.location.href = uri;
			});
		});
		
	});
	
})(jQuery);