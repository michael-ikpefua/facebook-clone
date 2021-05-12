package com.michael.service;

import com.michael.model.User;
import com.michael.repository.UserRepository;
import com.michael.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void store(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        User user = null;

        user = optionalUser.orElse(null);

        return  user;

    }

}
