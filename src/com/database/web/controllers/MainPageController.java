package com.database.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.database.web.beans.PageContextBean;

@Controller
public class MainPageController extends AbstractController{
	
	@Autowired
	private PageContextBean pageContext;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("Header", pageContext.getHeader());
		model.addObject("footer", pageContext.getFooter());
		model.addObject("menu", pageContext.getMenu());
		model.addObject("content", pageContext.getContent());
		model.addObject("title", pageContext.getTitle());
		model.addObject("encoding", pageContext.getEncoding());
		req.setAttribute("decriptor", pageContext);
		//String url = req.getRequestURL().toString();
		return model;
	}
	
}
