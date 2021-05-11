package com.michael.controller;

import com.michael.model.User;
import com.michael.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Registration {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("registration/store")
    public String store(@ModelAttribute("user") User user, HttpServletRequest request) {
        //Handle Validation

        //Login
        userService.store(user);

        //Add Session
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1000);
        session.setAttribute("user_session", user);

        return "redirect:/";


    }
}
