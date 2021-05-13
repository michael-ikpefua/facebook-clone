package com.michael.repository;

import com.michael.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    Optional<LikePost> findByUserIdAndPostId(long userId, long postId);

//    Optional <LikePost> deleteByUserIdAndPostId(long userId, long postId);

    int countLikePostByPostId(long id);
}
