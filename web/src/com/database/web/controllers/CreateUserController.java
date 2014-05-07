package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.jpa.PeopleService;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewUser;

@Controller
public class CreateUserController {

	private static final String SUCCESS_MESSAGE = "Пользователь '%s' успешно создан";
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("viewuser") ViewUser user, ModelMap model) {
		
		//FIXME add work with data base
		
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(view, pageContextBean);
		Modeller.addMessage(view, String.format(SUCCESS_MESSAGE, user.getNikname()));
		model.addAllAttributes(view.getModelMap());
		return "index";
	}

}
