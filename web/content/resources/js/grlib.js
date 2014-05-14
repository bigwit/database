/**
 * Modal windows graphical library
 */
(function($) {

	function WindowEngine() {
		this.idContent = null;

		this.BLOKER_DIV_ID = "curtain";
		this.HEAD_LINE_DIV = document.getElementById("headLineLbl");
		this.CONTENT_ENABLED_DIV = document.getElementById("mainSpace");
		this.CONTENT_DIV = document.getElementById("cntnt");
		this.WIN_TEMPLATE_ID = "window";
		this.DEFAULT_TITLE = "Информация";

	}

	WindowEngine.prototype.show = function(contentDivId, title) {
		if (this.idContent != null) {
			alert("Закройте предыдущее окно!");
			return;
		}
		var content = document.getElementById(contentDivId);
		if (content == null) {
			alert('не удалось =(');
			return;
		}
		for(var i = 0; content.childNodes.length > i; ++i) {
			content.childNodes[i].id = "viewport" + content.childNodes[i].nodeType;
		}
		// set title
		this.HEAD_LINE_DIV.innerHTML = title != null ? title
				: this.DEFAULT_TITLE;
		this.HEAD_LINE_DIV.innerHTML = "<span class=\"title-win\">" + this.HEAD_LINE_DIV.innerHTML + "</span>";
		// set content
		this.CONTENT_DIV.innerHTML = content.innerHTML;
		
		var closer = document.createElement("input");
		closer.value = "Закрыть";
		closer.type = "button";
		closer.id = "closer-button";
		closer.setAttribute("class", "button orange close");
		closer.addEventListener("click", function(event) {
			window.GrEngine.hide();
		});
		this.CONTENT_DIV.appendChild(closer);

		// bloked screen
		$("#" + this.BLOKER_DIV_ID).show("fast");
		// show window
		$("#" + this.WIN_TEMPLATE_ID).show("slow");
		this.idContent = contentDivId;
	};

	WindowEngine.prototype.hide = function() {
		if (this.idContent == null) {
			alert("окна уже закрыты");
			return;
		}
		$("#" + this.WIN_TEMPLATE_ID).hide("slow");
		$("#" + this.BLOKER_DIV_ID).hide("fast");
		this.idContent = null;

	};

	window.initWindowEngine = function() {
		if(window.GrEngine == null)
			window.GrEngine = new WindowEngine();
	};

})(jQuery);
