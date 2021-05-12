package com.michael.controller;

import com.michael.model.User;
import com.michael.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    IUserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String store(@ModelAttribute("user") User user, HttpServletRequest request) {
        //Handle Validation

        userService.store(user);

        //Add Session
        request.getSession().invalidate();
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1000);
        session.setAttribute("user_session", user);

        //Login
        return "redirect:/dashboard";


    }
}
