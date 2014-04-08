package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewUser;

@Controller
public class SwitchContentController {
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@RequestMapping(value="/create")
	public ModelAndView createPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.FORM_TAG_OBJECT_NAME, new ViewUser());
		pageContextBean.setContent(siteContent.getCreatePage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView searchPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW,
				Modeller.FORM_TAG_OBJECT_NAME, new ViewUser());
		pageContextBean.setContent(siteContent.getCreatePage());
		Modeller.addDefaultModels(model, pageContextBean);
		return model;
	}
	
}
