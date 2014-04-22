package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewSearchForm;
import com.database.web.services.SearchService;
import com.database.web.services.search.ResultSearch;

@Controller
public class SearchController {
	
	private static final String NOT_FOUND_MESSAGE = "По вашему запросу '%s' ничего не найдено";
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SiteContent siteContent;
	
	@RequestMapping(value = "/sch", method = RequestMethod.POST)
	public String search(@ModelAttribute("query") ViewSearchForm query, ModelMap model) {		
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getSearchPage());
		Modeller.addDefaultModels(view, pageContextBean);
		model.addAllAttributes(view.getModelMap());
		view.addObject("placeholderSearch", Modeller.PLACEHOLDER_SEARCH_LABEL);
		
		if(query != null && query.getQuery() != null && !query.getQuery().isEmpty()) {
			ResultSearch results = searchService.search(query);
			if(results == null || results.isErrorMessage()) {
				Modeller.addMessage(view, String.format(NOT_FOUND_MESSAGE, query.getQuery()));
			} else {
				view.addObject("valueQuery", query.getQuery());
				view.addObject("resultset", results.set());
			}
		}
		return "index";
	}
	
}
