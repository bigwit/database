package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewSearchForm;

public class SearchController {
	
	private static final String NOT_FOUND_MESSAGE = "По вашему запросу '%s' ничего не найдено";
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@RequestMapping(value = "/sch", method = RequestMethod.POST)
	public String search(@ModelAttribute("query") ViewSearchForm query, ModelMap model) {
		
		//FIXME add work with data base
		
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(view, pageContextBean);
		model.addAllAttributes(view.getModelMap());
		return "index";
	}
	
}
