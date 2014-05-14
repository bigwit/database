package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.domain.User;
import com.database.web.autorization.GridData;
import com.database.web.autorization.secure.SecureCookie;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;
import com.database.web.controllers.utils.Modeller;

@Controller
public class LogoutController {

	//private Logger log = Logger.getLogger(LogoutController.class.getName());

	@Autowired
	private PageContextBean pageContextBean;

	@Autowired
	private SiteContent siteContent;

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		GridData.getInstance().removeUser((User) request.getAttribute("USER"));
		request.setAttribute("USER", null);
		SecureCookie.resetCookie(response);
		
		ModelAndView model = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(model, pageContextBean, request);
		return model;
	}

}
