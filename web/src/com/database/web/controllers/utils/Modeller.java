package com.database.web.controllers.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.forms.ViewSearchForm;

public class Modeller {

	/**
	 * Базовые JSP используемые в качестве шаблона для постоения страниц.
	 */
	public static final String ROOT_VIEW = "index";

	public static final String ROOT_VIEW_EMPLOYEE = "besttour";

	public static final String ROOT_VIEW_ADMIN = "dnm";
	
	/**
	 * Объект, используемый для получения ModelAndView в качестве имени модели.
	 * Применяется на станицах где используются <form:form>
	 */
	public static final String VIEW_USER_ATTRIBUTE_NAME = "viewuser";
	
	public static final String VIEW_SEARCH_FORM_QUERY_NAME = "query";

	private static final String MESSAGE_KEY = "message";
	private static final String HEADER_KEY = "Header";
	private static final String FOOTER_KEY = "footer";
	
	public static final String UNKNOWN_ERROR_MESSAGE = "База данных временно не доступна";

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
			PageContextBean pageContext, HttpServletRequest request) {
		model.addObject(HEADER_KEY, pageContext.getHeader());
		model.addObject(FOOTER_KEY, pageContext.getFooter());
		model.addObject("menu", pageContext.getMenu());
		model.addObject("content", pageContext.getContent());
		model.addObject("title", pageContext.getTitle());
		model.addObject("encoding", pageContext.getEncoding());
		model.addObject(MESSAGE_KEY, "");
		model.addObject("query", new ViewSearchForm());
		model.addObject("placeholderSearch", PLACEHOLDER_SEARCH_LABEL);
		model.addObject("onlineUser", request.getAttribute("USER"));
		
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

	
	/**
	 * 
	 */

	public static final String PLACEHOLDER_SEARCH_LABEL = "Введите запрос";

	public static void setErrorMessage(ModelAndView model) {
		addMessage(model, UNKNOWN_ERROR_MESSAGE);
	}
	
	
}
