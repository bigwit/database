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
import com.database.web.forms.ViewUser;

@Controller
@RequestMapping(value="/main")
public class MainPageController {
	
	@Autowired
	private PageContextBean pageContext;
	
	@Autowired
	private SiteContent siteContent;


	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleGet(@ModelAttribute("viewuser") ViewUser user, ModelMap attrs) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContext.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContext);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handlePost(@ModelAttribute("viewuser") ViewUser user, ModelMap attrs) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContext.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContext);
		return model;
	}
	
}
