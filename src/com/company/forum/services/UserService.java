package com.company.forum.services;

import com.company.forum.model.User;

public interface UserService {

    void registerUser(String email, String password, String role);

    User getById(Integer userId);

    User getByEmail(String email);

    String hashPassword(String password);

    boolean checkPassword(String email, String password);
}

