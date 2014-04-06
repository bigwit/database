package com.database.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.web.beans.PageContextBean;

@Controller
public class MainPageController {

	@Autowired
	private PageContextBean pageContext;
	
	@RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }
	
}
