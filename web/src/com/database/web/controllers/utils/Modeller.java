package com.database.web.controllers.utils;

import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;

public class Modeller {

	/**
	 * Базовая JSP используемая в качестве шаблона для постоения страницы.
	 */
	public static final String ROOT_VIEW = "index";

	/**
	 * Объект, используемый для получения ModelAndView в качестве имени модели.
	 * Применяется на станицах где используются <form:form>
	 */
	public static final String VIEW_USER_ATTRIBUTE_NAME = "viewuser";
	
	public static final String VIEW_SEARCH_FORM_QUERY_NAME = "query";

	private static final String MESSAGE_KEY = "message";
	private static final String HEADER_KEY = "Header";
	private static final String FOOTER_KEY = "footer";

	/**
	 * Метод вытаскивает баозвый контекст любой страницы из бина (хранилища
	 * параметров страницы) и заполняем полученными параметрами модель
	 * представоения.
	 * 
	 * @param model
	 *            ModelAndView spring mvc object
	 * @param pageContext
	 *            source of model parameters
	 */
	public static void addDefaultModels(ModelAndView model,
			PageContextBean pageContext) {
		model.addObject(HEADER_KEY, pageContext.getHeader());
		model.addObject(FOOTER_KEY, pageContext.getFooter());
		model.addObject("menu", pageContext.getMenu());
		model.addObject("content", pageContext.getContent());
		model.addObject("title", pageContext.getTitle());
		model.addObject("encoding", pageContext.getEncoding());
		model.addObject(MESSAGE_KEY, "");
	}

	/**
	 * Добавляет в модель сообщение, которое необходимо показать
	 *  пользователю сразу после загрузки страницы.
	 * 
	 * @param modell
	 *            ModelAndView spring mvc object
	 * @param msg 
	 * 			  display message
	 */
	public static void addMessage(ModelAndView model, String msg) {
		model.addObject(MESSAGE_KEY, msg);
	}

}
