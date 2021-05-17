package com.michael.controller;

import com.michael.model.User;
import com.michael.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("user_not_found", null);

        User authUser = userService.login(user.getEmail(), user.getPassword());
        if (authUser == null) {
            redirectAttributes.addFlashAttribute("user_not_found", "Email or Password is Incorrect");
            return "redirect:/";
        } else {
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1000);
            session.setAttribute("user_session", authUser);
            redirectAttributes.addFlashAttribute("login_success", "Welcome to your dashboard " + authUser.getFullName());

            return "redirect:/dashboard";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
