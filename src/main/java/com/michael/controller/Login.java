package com.michael.controller;

import com.michael.model.User;
import com.michael.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpServletRequest request)
    {
        //Handle Validation...
        User authUser = userService.login(user.getEmail(), user.getPassword());
        if (authUser == null) {
            //User is not logged in.
        } else {
            request.getSession().invalidate();
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(1000);
            session.setAttribute("user_session", user);
            return "redirect:/dashboard";
        }

        return "";


    }
}
