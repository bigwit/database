(function($) {

	$(document)
			.ready(
					function() {

						window.initWindowEngine();

						window.showMessage = function() {
							var message = document.getElementById("message").innerHTML
									.trim();

							if (message != null && message != "") {
								window.setTimeout(function() {
									$("#message").show('slow');
								}, 0);
								window.setTimeout(function() {
									$("#message").hide('slow');
								}, 3000);
							}
						};
						window.showMessage();

						$("[data-menu]").each(function(i, elem) {
							$(elem).click(function() {
								var uri = $(elem).attr('id');
								window.location.href = uri;
							});
						});

						$("#_devs").click(
								function(e) {
									e.preventDefault();
									window.GrEngine.show("__stud_devs_",
											"Разработчики приложения");
								});

						$("#_lic").click(
								function(e) {
									e.preventDefault();
									window.GrEngine.show("__stud_lic_",
											"Лицензия настоящего ПО");
								});

						$("#_clb").click(
								function(e) {
									e.preventDefault();
									window.GrEngine.show("__stud_clb_",
											"Скромная служба поддержки");
								});
						
						$("[data-add-comment]").each(function(i, elem) {
							$(elem).click(function(e) {
								window.GrEngine.show("add_comm_office", "Добавить комментарий");
							});
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

						$("#join")
								.click(
										function(e) {

											var passwd = document
													.getElementById("pswd").value;
											var cpasswd = document
													.getElementById("cpswd").value;

											if (passwd == null
													|| cpasswd == null
													|| passwd != cpasswd
													|| passwd == ''
													|| cpasswd == ''
													|| passwd.length < 0) {

												$("#message")
														.html(
																"неверно задан пароль или его подтверждение");
												window.showMessage();
												return false;
											}
											
											var nik = document.getElementById("nikname").value;
											if(nik == null || nik == '') {
												$("#message")
														.html("Поле логин не может быть пустым");
												window.showMessage();
												return false;
											}

											return true;
										});

					});

})(jQuery);