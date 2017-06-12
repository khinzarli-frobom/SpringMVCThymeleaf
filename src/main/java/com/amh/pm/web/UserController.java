package com.amh.pm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.amh.pm.entity.User;
import com.amh.pm.service.UserService;

@Controller
public class UserController {
	private UserService userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public String loginUser(@Validated User user, BindingResult result, Model model, HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		String name = user.getName();
		String password = user.getPassword();
		User u = userService.userByName(name, password);

		if (u == null) {
			String errormessage =  "Username or Password Do Not Correct";
			model.addAttribute("message",errormessage);
			return "login";
		} else {
			session.setAttribute("userId", u.getId());
			session.setAttribute("name", u.getName());
			session.setAttribute("password", u.getPassword());
		}
		return "redirect:organizations";

	}

	/*
	 * @RequestMapping(value = "/signup", method = RequestMethod.GET) public
	 * String showSignupForm(Model model) { model.addAttribute("user", new
	 * User()); return "signup"; }
	 * 
	 * @RequestMapping(value = "/user", method = RequestMethod.POST) public
	 * String addCustomer(@Validated @ModelAttribute("userForm") User user,
	 * BindingResult result, Model model) { userService.save(user); return
	 * "redirect:users"; }
	 * 
	 * @RequestMapping(value = "/users", method = RequestMethod.GET) public
	 * String list(Model model) { model.addAttribute("users",
	 * this.userService.findAll()); return "users"; }
	 * 
	 * @RequestMapping(value = "users/{id}", method = RequestMethod.GET) public
	 * String showDetails(@PathVariable("id") int id, Model model) {
	 * model.addAttribute("user", userService.findById(id)); return
	 * "userDetails"; }
	 */
}