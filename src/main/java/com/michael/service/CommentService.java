package com.michael.service;

import com.michael.model.Comment;
import com.michael.model.Post;
import com.michael.model.User;
import com.michael.repository.CommentRepository;
import com.michael.service.contracts.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void addComment(User user, Post post, String body) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setBody(body);

        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
