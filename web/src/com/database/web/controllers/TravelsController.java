package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.jpa.TravelService;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;

@Controller
public class TravelsController {

	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@Autowired
	private TravelService travelService;
	
	@RequestMapping(value="/clients")
	public ModelAndView clientsPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getClientsPage());
		Modeller.addDefaultModels(model, pageContextBean);
		model.addObject("onlineUser", request.getAttribute("USER"));
		try {
			model.addObject("travels", travelService.findAll());
		} catch (Exception e) {
			Modeller.setErrorMessage(model);
		}
		return model;
	}
	
}
