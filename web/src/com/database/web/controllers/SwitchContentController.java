package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;

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
	protected ModelAndView handleGet(@ModelAttribute("viewuser") ViewUser user, ModelMap attrs, HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}
	
	@RequestMapping(value="/create")
	public ModelAndView createPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.VIEW_USER_ATTRIBUTE_NAME, new ViewUser());
		pageContextBean.setContent(siteContent.getCreatePage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView searchPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getSearchPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}
	
	@RequestMapping(value="/desc")
	public ModelAndView descPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDescriptorPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView aboutPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getAboutPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}
}
