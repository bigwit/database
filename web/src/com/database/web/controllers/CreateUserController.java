package com.database.web.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.database.data.domain.Contact;
import com.database.data.domain.Location;
import com.database.data.domain.People;
import com.database.data.domain.User;
import com.database.data.jpa.UserService;
import com.database.web.autorization.GridData;
import com.database.web.autorization.secure.Role;
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
	private UserService userService;
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("viewuser") ViewUser user, ModelMap model,
			HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView(Modeller.ROOT_VIEW);
		pageContextBean.setContent(siteContent.getDefaultPage());
		Modeller.addDefaultModels(view, pageContextBean, request);

		User newUser = null;
		try {
			newUser = getUserBySource(user);
		} catch (ParseException e1) {
			log.warning("Cot valid date format");
			e1.printStackTrace();
			Modeller.addMessage(view, "Не удалось создать учетную запись. Попробуйте еще раз.");
			pageContextBean.setContent(siteContent.getCreatePage());
			return "index";
		}

		try {
			// add user to DB
			userService.registerUser(newUser);
			// register user in GridDataInMemory
			String secureCookie = GridData.getInstance().addUser(newUser);
			// сохраняем в модели и выставляем куку
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
	
	private User getUserBySource(ViewUser source) throws ParseException {
		Location loc = new Location();
		loc.setCity(source.getCity());
		loc.setCountry(source.getCountry());
		loc.setDescription("нет дополнительной информации");
		
		Contact cont = new Contact();
		cont.setEmail(source.getEmail());
		cont.setPhone(source.getPhone());
		cont.setLocation(loc);
		
		People people = new People();
		people.setContact(cont);
		java.util.Date d = (new SimpleDateFormat("dd-MM-yyyy")).parse(source.getDate());
		people.setDateBirth(new Date(d.getTime()));
		people.setFirstName(source.getFirstName());
		people.setLastName(source.getLastName());
		people.setMiddleName("");
		people.setSex(source.getSex());
		
		User user = new User();
		user.setPeople(people);
		user.setRole(Role.USER.toString());
		user.setHashPasswd(GridData.getMD5(source.getPassword()));
		user.setLogin(source.getNikname());
		
		return user;
	}

}
