package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.database.web.beans.PageContextBean;
import com.database.web.controllers.utils.Modeller;

@Controller
public class MainPageController extends AbstractController{
	
	@Autowired
	private PageContextBean pageContext;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		Modeller.addDefaultModels(model, pageContext);
		return model;
	}
	
}
