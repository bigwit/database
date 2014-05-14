package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.jpa.ClientService;
import com.database.data.jpa.CommentService;
import com.database.data.jpa.PeopleService;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;

@Controller
public class PersonalCabinetController {

	@Autowired
	private PageContextBean pageContextBean;

	@Autowired
	private SiteContent siteContent;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "/cabinet")
	public ModelAndView getMyCabinet(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("cabinet");
		model.addObject("onlineUser", request.getAttribute("USER"));
		return model;
	}
	
	@RequestMapping(value = "/updateprofile")
	public ModelAndView updateCabinet(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("cabinet");
		
		// update user in database
		
		model.addObject("onlineUser", request.getAttribute("USER"));
		return model;
	}
}
