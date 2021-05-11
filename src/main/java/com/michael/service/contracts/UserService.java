package com.michael.service.contracts;

import com.michael.model.User;

public interface UserService {

    void store(User user);

    User login(String email, String password);
}
