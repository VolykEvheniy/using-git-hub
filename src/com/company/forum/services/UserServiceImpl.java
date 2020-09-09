package com.company.forum.services;


import com.company.forum.database.Database;
import com.company.forum.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void registerUser(String email, String password, String role) {
        Map<String, User> users = database.getUsers();
        Integer userId = users.size();
        String passwordHash = hashPassword(password);
        User newUser = new User(userId, email, passwordHash, role);
        users.put(email, newUser);
    }

    @Override
    public User getById(Integer userId) {
        List<User> users = database.getUsers().values().stream()
                .filter(u -> u.getUserId().equals(userId))
                .collect(Collectors.toList());
        return users.get(0);
    }

    @Override
    public User getByEmail(String email) {
        return database.getUsers().get(email);
    }

    @Override
    public String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean checkPassword(String email, String password) {
        String passHash = hashPassword(password);
        User user = getByEmail(email);
        if (user.getPasswordHash().equals(passHash)) {
            return true;
        }
        return false;
    }
}
