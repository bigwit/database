package com.database.web.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.domain.User;
import com.database.data.jpa.PeopleService;
import com.database.web.autorization.GridData;
import com.database.web.autorization.secure.SecureCookie;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;
import com.database.web.forms.ViewUser;

@Controller
public class CreateUserController {

	private static final String SUCCESS_MESSAGE = "Пользователь '%s' успешно создан";
	
	private Logger log = Logger.getLogger(CreateUserController.class.getName());
	
	@Autowired
	private PageContextBean pageContextBean;
	
	@Autowired
	private SiteContent siteContent;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("viewuser") ViewUser user, ModelMap model,
			HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(view, pageContextBean, request);

		User newUser = new User();
		// init new User
		newUser.setLogin(user.getNikname());
		newUser.setHashPasswd(GridData.getMD5(user.getPassword()));
		//... other
		
		// add user to database here
		
		try {
			String secureCookie = GridData.getInstance().addUser(newUser);
			// сохранить в модели и выставить куку
			view.addObject("onlineUser", newUser);
			SecureCookie.setCookie(response, secureCookie);
			
			Modeller.addMessage(view, String.format(SUCCESS_MESSAGE, user.getNikname()));
		} catch (Exception e) {
			Modeller.addMessage(view, "ошибра регистрации. обратитесь в службу поддержки");
			log.warning("ERROR REGISTER USER: \n" + e.getMessage() + "\n" + e);
		}
		
		model.addAllAttributes(view.getModelMap());
		return "index";
	}

}
