package com.database.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	private List<TourInfo> allResults;
	
	private int currentPage = 1;

	@RequestMapping(value = "/sch", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("query") ViewSearchForm query,
			ModelMap model, HttpServletRequest request) {
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getSearchPage());
		Modeller.addDefaultModels(view, pageContextBean, request);
		model.addAllAttributes(view.getModelMap());
		try {
			if (query != null && query.getQuery() != null
					&& !query.getQuery().isEmpty()) {
				allResults = searchService.search(query.getQuery(),
						getSearchTypeByQuery(query));
				if (allResults == null || allResults.size() == 0) {
					Modeller.addMessage(view,
							String.format(NOT_FOUND_MESSAGE, query.getQuery()));
				} else {
					resetPaging();
					view.addObject("valueQuery", query.getQuery());
					view.addObject("resultset", getPageTours(currentPage));
					view.addObject("pages", getCountPages());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Modeller.addMessage(view, "Не удалось связаться с базой данных");
		}
		return view;
	}
	
	@RequestMapping(value = "/more", method = RequestMethod.POST)
	public ModelAndView moreResults(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("mresult");
		if(currentPage < getCountPages()) {
			view.addObject("resultset", getPageTours(++currentPage));
		} else {
			response.setStatus(500);
		}
		return view;
	}
	
	private void resetPaging() {
		this.currentPage = 1;
	}

	private List<TourInfo> getPageTours(int numberPage) {
		int count = getCountPages();
		if(numberPage > count || numberPage < 1) {
			return getPageTours(1);
		}
		int toIndex = numberPage * 10 - 1;
		if(toIndex > allResults.size()) {
			toIndex = allResults.size() - 1;
		}
		return allResults.subList((numberPage - 1) * 10, toIndex);
	}
	
	private int getCountPages() {
		int countPages = allResults.size() / 10;
		countPages += allResults.size() % 10 != 0 ? 1 : 0;
		return countPages;
	}

	private SearchType getSearchTypeByQuery(ViewSearchForm form) {
		switch (form.getType()) {
		case "simple":
			return SearchType.SIMPLE;
		case "algo1":
			return SearchType.LEVENSTEIN;
		case "algo2":
			return SearchType.JARO_WINKLER;
		case "algo3":
			return SearchType.SIMPLE;
		default:
			return SearchType.SIMPLE;
		}
	}

}
