(function($) {
	
	$(document).ready(function() {
		
		window.initWindowEngine();
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
		
		$("#_devs").click(function(e) {
			e.event.preventDefault();
			window.GrEngine.show("__stud_devs_", "Разработчики приложения");
		});

		$("#_lic").click(function(e) {
			e.preventDefault();
			window.GrEngine.show("__stud_lic_", "Лицензия настоящего ПО");
		});

		$("#_clb").click(function(e) {
			e.preventDefault();
			window.GrEngine.show("__stud_clb_", "Скромная служба поддержки");
		});
		
	});
	
})(jQuery);