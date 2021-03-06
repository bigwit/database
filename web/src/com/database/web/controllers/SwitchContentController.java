package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewSearchForm;
import com.database.web.forms.ViewUser;

@Controller
public class SwitchContentController {
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@RequestMapping(value="/main")
	protected ModelAndView handleGet(@ModelAttribute("viewuser") ViewUser user, ModelMap attrs) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.VIEW_SEARCH_FORM_QUERY_NAME, new ViewSearchForm());
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContextBean);
		model.addObject("placeholderSearch", Modeller.PLACEHOLDER_SEARCH_LABEL);
		return model;
	}
	
	@RequestMapping(value="/create")
	public ModelAndView createPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.VIEW_USER_ATTRIBUTE_NAME, new ViewUser());
		pageContextBean.setContent(siteContent.getCreatePage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView searchPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.VIEW_SEARCH_FORM_QUERY_NAME, new ViewSearchForm());
		pageContextBean.setContent(siteContent.getSearchPage());
		Modeller.addDefaultModels(model, pageContextBean);
		model.addObject("placeholderSearch", Modeller.PLACEHOLDER_SEARCH_LABEL);
		return model;
	}
	
	@RequestMapping(value="/clients")
	public ModelAndView clientsPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getClientsPage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
	
	@RequestMapping(value="/desc")
	public ModelAndView descPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDescriptorPage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView aboutPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getAboutPage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
}
