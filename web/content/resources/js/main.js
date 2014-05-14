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

						window.send = function(sender, eventArgs) {
							if (!eventArgs && !eventArgs.type) {
								return;
							}
							if (eventArgs.type == 'comment') {
								var headInput = $('div#viewport1')[1].childNodes[7];
								var textInput = $('div#viewport1')[1].childNodes[16];
								$.ajax({
									type : "POST",
									url : "/database/addcomment",
									data : {
										titleText : headInput.value,
										text : textInput.value,
										office: window.currCommWinId
									}
								}).done(function(msg) {
									$("#closer-button").click();
									$("#message")
									.html("Комментарий успешно добавлен");
									window.showMessage();
								}).fail(function() {
									$("#closer-button").click();
									$("#message")
									.html("Не удалось добавить комментарий =(");
									window.showMessage();
								});
								window.currCommWinId = null;
							}
						};

						window.post = function(path, params, method) {
							method = method || "post";

							var form = document.createElement("form");
							form.setAttribute("method", method);
							form.setAttribute("action", path);

							for ( var key in params) {
								if (params.hasOwnProperty(key)) {
									var hiddenField = document
											.createElement("input");
									hiddenField.setAttribute("type", "hidden");
									hiddenField.setAttribute("name", key);
									hiddenField.setAttribute("value",
											params[key]);
									form.appendChild(hiddenField);
								}
							}
							document.body.appendChild(form);
							form.submit();
						};
						
						window.getPersonalData = function() {
							
							$.ajax({
								type : "POST",
								url : "/database/cabinet",
								data : {
									titleText : headInput.value,
									text : textInput.value,
									office: window.currCommWinId
								}
							}).done(function(msg) {
								$("#closer-button").click();
								$("#message")
								.html("Комментарий успешно добавлен");
								window.showMessage();
							}).fail(function() {
								$("#closer-button").click();
								$("#message")
								.html("Не удалось добавить комментарий =(");
								window.showMessage();
							});
							
						};

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

						$("[data-add-comment]").each(
							function(i, elem) {
								$(elem).click(function(e) {
									window.currCommWinId = e.currentTarget.id;
									window.GrEngine.show("add_comm_office","Добавить комментарий");
								});
						});
						
						$("[data-show-comment]").each(
							function(i, elem) {
								$(elem).click(function(e) {
									$.ajax({
										type : "POST",
										url : "/database/getcomments",
										data : {
											officeId : e.currentTarget.id
										}
									}).done(function(msg) {
										if(msg == null || msg == '') {
											return;
										} 
										var div = document.createElement("div");
										div.style.display = "none";
										div.id="allComments";
										div.innerHTML = msg;
										window.GrEngine.show("allComments", "Комментарии к офису");
									}).fail(function() {
										$("#closer-button").click();
										$("#message")
										.html("Невозможно показать коммeнтарии");
										window.showMessage();
									});
								});
						});

						$("#signin").click(function(e) {
							e.preventDefault();
							var _login = $("#25dfddh6nf").val();
							var _passwd = $("#df5h1hyj35fu").val();

							window.post("/database/login", {
								login : _login,
								passwd : _passwd
							}, "post");

							// var form = document.createElement("form");
							// form.setAttribute("method", "post");
							// form.setAttribute("action", "/database/login");
							// var hiddenField1 =
							// document.createElement("input");
							// hiddenField1.setAttribute("type", "hidden");
							// hiddenField1.setAttribute("name", "login");
							// hiddenField1.setAttribute("value", login);
							// var hiddenField =
							// document.createElement("input");
							// hiddenField.setAttribute("type", "hidden");
							// hiddenField.setAttribute("name", "passwd");
							// hiddenField.setAttribute("value", passwd);
							// form.appendChild(hiddenField);
							// form.appendChild(hiddenField1);
							// document.body.appendChild(form);
							// form.submit();
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

											var nik = document
													.getElementById("nikname").value;
											if (nik == null || nik == '') {
												$("#message")
														.html(
																"Поле логин не может быть пустым");
												window.showMessage();
												return false;
											}

											return true;
										});

					});

})(jQuery);