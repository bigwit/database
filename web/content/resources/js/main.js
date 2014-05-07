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
			e.preventDefault();
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
		
		$("#signin").click(function(e) {
			e.preventDefault();
			
			var login = $("#25dfddh6nf").val();
			var passwd = $("#df5h1hyj35fu").val();
			
			var form = document.createElement("form");
		    form.setAttribute("method", "post");
		    form.setAttribute("action", "/database/login");
		    
		    var hiddenField1 = document.createElement("input");
		    hiddenField1.setAttribute("type", "hidden");
		    hiddenField1.setAttribute("name", "login");
		    hiddenField1.setAttribute("value", login);
		    var hiddenField = document.createElement("input");
		    hiddenField.setAttribute("type", "hidden");
		    hiddenField.setAttribute("name", "passwd");
		    hiddenField.setAttribute("value", passwd);

		    form.appendChild(hiddenField);
		    form.appendChild(hiddenField1);
		    document.body.appendChild(form);
		    form.submit();
			
		});
		
	});
	
})(jQuery);