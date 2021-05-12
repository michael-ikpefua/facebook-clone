package com.michael.service.contracts;

import com.michael.model.Post;
import com.michael.model.User;
import org.springframework.stereotype.Service;

public interface ICommentService {

    void addComment(User user, Post post, String body);
}
