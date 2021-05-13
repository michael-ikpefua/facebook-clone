package com.michael.service.contracts;

import com.michael.model.Post;
import com.michael.model.User;

public interface ILikePostService {
    void likeOrUnLikePost(User user, Post post);

    boolean checkIfUserLikePost(long userId, long postId);

    int totalNumberOfLikesOnPost(long postId);

}
