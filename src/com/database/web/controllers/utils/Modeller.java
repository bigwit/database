package com.database.web.controllers.utils;

import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;

public class Modeller {
	
	public static final String ROOT_VIEW = "index";
	
	public static final String FORM_TAG_OBJECT_NAME = "command";
	
	public static void addDefaultModels(ModelAndView model, PageContextBean pageContext) {
		model.addObject("Header", pageContext.getHeader());
		model.addObject("footer", pageContext.getFooter());
		model.addObject("menu", pageContext.getMenu());
		model.addObject("content", pageContext.getContent());
		model.addObject("title", pageContext.getTitle());
		model.addObject("encoding", pageContext.getEncoding());
	}
	
}
