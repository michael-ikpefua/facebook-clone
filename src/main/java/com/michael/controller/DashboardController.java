package com.michael.controller;

import com.michael.model.Comment;
import com.michael.model.Post;
import com.michael.model.User;
import com.michael.service.contracts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class DashboardController {

    @Autowired
    IPostService iPostService;

    @GetMapping("/dashboard")
    public String index(Model model)
    {
        Iterable<Post> posts = iPostService.getAllPost();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());

        return "dashboard";

    }

    @PostMapping("/post")
    public String store(@ModelAttribute("post") Post post, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("user_session");

        iPostService.addPost(post.getBody(), loggedInUser);

        return "redirect:/dashboard";
    }

    @GetMapping("post/{id}")
    @Transactional
    public String show(@PathVariable(value = "id") long id, Model model) {

        Post post = iPostService.getPostById(id);
        if (post == null) {
            //handle Exception
            return "";
        }

        Set<Comment> comments = post.getComment();


        model.addAttribute("comment", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-details";
    }

    @PostMapping("/post/update")
    public String update(@ModelAttribute("post") Post post) {

        iPostService.updatePost(post.getId(), post.getBody());

        return "redirect:/post/" + post.getId();
    }

    @PostMapping("/post/delete")
    public String delete(@ModelAttribute("post") Post post) {
        iPostService.deletePost(post.getId());

        return "redirect:/dashboard";

    }

}
