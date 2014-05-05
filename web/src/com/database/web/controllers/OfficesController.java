package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.services.OfficesService;

@Controller
public class OfficesController {

	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@Autowired
	private OfficesService officesService;
	
	@RequestMapping(value="/offices")
	public ModelAndView officesPage() {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getOfficesPage());
		Modeller.addDefaultModels(model, pageContextBean);
		model.addObject("offices", officesService.getAllOffices());
		return model;
	}
	
}
