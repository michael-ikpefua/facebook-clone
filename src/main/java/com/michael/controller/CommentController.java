package com.michael.controller;

import com.michael.model.Comment;
import com.michael.model.Post;
import com.michael.model.User;
import com.michael.service.contracts.ICommentService;
import com.michael.service.contracts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    IPostService iPostService;

    @Autowired
    ICommentService iCommentService;

    @PostMapping("/comment/{id}")
    public String store(@PathVariable(value = "id") long id, @ModelAttribute("comment")Comment comment, HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("user_session");
        Post post = iPostService.getPostById(id);

        iCommentService.addComment(authenticatedUser, post, comment.getBody());

        return "redirect:/post/" + post.getId();

    }
    @PostMapping("/comment/{id}/delete/{postId}")
    public String delete(@PathVariable(value = "id") long id, @PathVariable(value = "postId") long postId, RedirectAttributes redirectAttributes) {
        iCommentService.deleteComment(id);

        redirectAttributes.addFlashAttribute("comment_delete", "Comment Deleted Successfully");

        return "redirect:/post/" + postId;

    }
}
