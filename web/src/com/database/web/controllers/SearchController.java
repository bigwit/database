package com.database.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.domain.TourInfo;
import com.database.data.jpa.SearchService;
import com.database.data.type.SearchType;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewSearchForm;

@Controller
public class SearchController {
	
	private static final String NOT_FOUND_MESSAGE = "По вашему запросу '%s' ничего не найдено";
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/sch", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("query") ViewSearchForm query, 
			ModelMap model, HttpServletRequest request) {		
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getSearchPage());
		Modeller.addDefaultModels(view, pageContextBean, request);
		model.addAllAttributes(view.getModelMap());
		
		if(query != null && query.getQuery() != null && !query.getQuery().isEmpty()) {
			List<TourInfo> results = searchService.search(query.getQuery(), getSearchTypeByQuery(query));
			if(results == null || results.size() == 0) {
				Modeller.addMessage(view, String.format(NOT_FOUND_MESSAGE, query.getQuery()));
			} else {
				view.addObject("valueQuery", query.getQuery());
				view.addObject("resultset", results);
			}
		}
		return view;
	}
	
	private SearchType getSearchTypeByQuery(ViewSearchForm form) {
		switch(form.getType()) {
			case "simple" : return SearchType.SIMPLE;
			case "algo1" : return SearchType.LEVENSTEIN;
			case "algo2" : return SearchType.VINKLER;
			case "algo3" : return SearchType.SIMPLE;
				default: return SearchType.SIMPLE;
		}
	}
	
}
