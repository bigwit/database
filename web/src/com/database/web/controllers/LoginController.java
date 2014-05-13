package com.database.web.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.domain.User;
import com.database.data.jpa.UserService;
import com.database.web.autorization.GridData;
import com.database.web.autorization.secure.Role;
import com.database.web.autorization.secure.SecureCookie;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;

@Controller
public class LoginController {

	private Logger log = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private PageContextBean pageContextBean;

	@Autowired
	private SiteContent siteContent;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {

		String passwd = GridData.getMD5(request.getParameter("passwd"));
		String login = request.getParameter("login");
		log.info("Handl request: login = " + login + ", password = " + passwd);
		// достать из базы
		User user = userService.getUser(login, passwd);
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		if (user == null) {
			pageContextBean.setContent(siteContent.getDefaultPage());
			Modeller.addDefaultModels(model, pageContextBean, request);
			Modeller.addMessage(model,
					"Пользователя с такой комбинацией логина и пароля не существует");
			return model;
		}
		if (user.getRole() == Role.ADMIN.toString()) {
			model = new ModelAndView(Modeller.ROOT_VIEW_ADMIN);
		}
		if (user.getRole() == Role.EMPLOYEE.toString()) {
			model = new ModelAndView(Modeller.ROOT_VIEW_EMPLOYEE);
		}
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		// авторизовать юзера в гриде
		String secureCookie = GridData.getInstance().addUser(user);
		// сохранить в модели и выставить куку
		model.addObject("onlineUser", user);
		SecureCookie.setCookie(response, secureCookie);

		return model;
	}

}
