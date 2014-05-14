package com.database.web.controllers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.data.domain.User;
import com.database.data.jpa.ClientService;
import com.database.data.jpa.CommentService;
import com.database.web.beans.PageContextBean;
import com.database.web.beans.SiteContent;

@Controller
public class CommentsController {


	private Logger log = Logger.getLogger(CommentsController.class.getName());

	@Autowired
	private PageContextBean pageContextBean;

	@Autowired
	private SiteContent siteContent;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/addcomment")
	public void addComment(HttpServletRequest request,
			HttpServletResponse response) {
		String text = request.getParameter("text");
		String id = request.getParameter("office");
		String header = request.getParameter("titleText");
		User user = (User)request.getAttribute("USER");
		log.info("ADD COMMENT: TEXT = " + text + ", id_office = " + id + " HEADER = " + header);
		user.getId();
		// write to BD
		//commentService.addComment(text, null, idClient, null, idOffice);
	}
	
}
