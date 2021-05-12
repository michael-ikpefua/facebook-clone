package com.michael.service.contracts;

import com.michael.model.Post;
import com.michael.model.User;

public interface IPostService {

    Iterable<Post> getAllPost();

    void addPost(String body, User user);

    Post getPostById(long id);

    void updatePost(long id, String body);

    void deletePost(long id);

}
