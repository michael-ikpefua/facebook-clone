package com.michael.service;

import com.michael.model.Post;
import com.michael.model.User;
import com.michael.repository.PostRepository;
import com.michael.service.contracts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable<Post> getAllPost() {
       return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void addPost(String body, User user) {
        Post post = new Post();
        post.setBody(body);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public Post getPostById(long id) {
        Post post = null;

        Optional<Post> optionalPost =  postRepository.findById(id);

        post = optionalPost.orElse(null);

       return post;
    }

    @Override
    public void updatePost(long id, String body) {
        Post post = this.getPostById(id);
        post.setBody(body);
        postRepository.save(post);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
