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
            String errormessage = "Username or Password Do Not Correct";
            model.addAttribute("message", errormessage);
            return "login";
        } else {
            session.setAttribute("userId", u.getId());
            session.setAttribute("name", u.getName());
            session.setAttribute("password", u.getPassword());
        }
        return "redirect:organizations";
    }

    @RequestMapping(value = "/registeration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerationuser", method = RequestMethod.POST)
    public String addUser(@Validated User user, BindingResult result, Model model, HttpServletRequest req) {

        String userName = user.getName();
        String userEmail = user.getEmail();

        User userNameCheck = userService.findUserIdByName(userName);
        User userEmailCheck = userService.findUserIdByEmail(userEmail);
        
        
        if (result.hasErrors()) {

            // if validator failed

            return "register";}
            else{

        if (userNameCheck == null && userEmailCheck == null) {
            
            

            userService.save(user);
            return "redirect:login";
        } else if (userNameCheck != null && userEmailCheck == null) {
            model.addAttribute("user", new User());
            model.addAttribute("userError", "User Nmae Already Exists!");
            return "register";
        } else if (userNameCheck == null && userEmailCheck != null) {

            model.addAttribute("user", new User());
            model.addAttribute("emailError", "User Email Already Exists!");
            return "register";
        } else {

            String error = "User Name And Email Already Exist";
            model.addAttribute("user", new User());
            model.addAttribute("errorMessage", error);
            return "register";
        }
    }
}
}