package com.database.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.database.data.domain.Comment;
import com.database.data.domain.People;
import com.database.data.domain.User;
import com.database.data.jpa.ClientService;
import com.database.data.jpa.CommentService;
import com.database.data.jpa.PeopleService;
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
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value="/addcomment")
	public void addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String text = request.getParameter("text");
		String id = request.getParameter("office");
		long officeId = 0;
		try {
			officeId = Long.parseLong(id);
		} catch (Exception ex) {
			// need return status 500
			response.setStatus(500);
			return;
		}
		String header = request.getParameter("titleText");
		User user = (User)request.getAttribute("USER");
		
		People people = user.getPeople();
		log.info(people.toString());
		Long peopleId = people.getId();
		log.info("people id: " + peopleId);
		long clientId = clientService.findByPeopleId(peopleId).getId();

		String.format("Add comment: \nheader = %s, \ntext = %s, "
				+ "\noffice id = %s, \nclient id = %s", 
				header,  text, id, String.valueOf(clientId));
		log.info("ADD COMMENT: TEXT = " + text + ", id_office = " + id + " HEADER = " + header);

		commentService.addComment(header + ":\n" + text, null, clientId, null, officeId);
	}
	
	@RequestMapping(value = "/getcomments")
	public ModelAndView getAllCommentsForOffice(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("comments");
		log.info("Office id = " + request.getParameter("officeId"));
		Long officeId = 1L;
		try {
			officeId = Long.parseLong(request.getParameter("officeId"));
		} catch(Exception ex) {	}
		List<Comment> comments = commentService.findCommentsByOffice(officeId);
		model.addObject("comms", comments);
		return model;
	}
	
}
