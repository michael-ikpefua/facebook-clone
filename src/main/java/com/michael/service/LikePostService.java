package com.michael.service;

import com.michael.model.LikePost;
import com.michael.model.Post;
import com.michael.model.User;
import com.michael.repository.LikePostRepository;
import com.michael.service.contracts.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostService implements ILikePostService {

    @Autowired
    LikePostRepository likePostRepository;

    @Override
    public void likeOrUnLikePost(User user, Post post) {
        LikePost likePost = null;
        if (!this.checkIfUserLikePost(user.getId(), post.getId())) {

            likePost = new LikePost();
            likePost.setUser(user);
            likePost.setPost(post);

            likePostRepository.save(likePost);
        } else {
            Optional<LikePost> optionalLikePost = likePostRepository.findByUserIdAndPostId(user.getId(), post.getId());
            if (optionalLikePost.isPresent()) {
                likePost = optionalLikePost.get();
                likePostRepository.delete(likePost);
            }
        }
    }

    @Override
    public boolean checkIfUserLikePost(long userId, long postId) {

        Optional<LikePost> optionalLikePost =  likePostRepository.findByUserIdAndPostId(userId, postId);
        return optionalLikePost.isPresent();
    }

    @Override
    public int totalNumberOfLikesOnPost(long postId) {

        return likePostRepository.countLikePostByPostId(postId);

    }


}
