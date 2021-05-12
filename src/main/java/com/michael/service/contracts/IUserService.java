package com.michael.service.contracts;

import com.michael.model.User;

public interface IUserService {

    void store(User user);

    User login(String email, String password);
}
