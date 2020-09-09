package com.company.forum.testData;

import com.company.forum.database.Database;
import com.company.forum.services.PostService;
import com.company.forum.services.TopicService;
import com.company.forum.services.UserService;

import java.util.Date;

public class TestData {

    public static void addTestData(UserService userService, TopicService topicService, PostService postService) {
        Date date = new Date();

        userService.registerUser("admin@mail.com", "admin", "admin");
        userService.registerUser("user1@mail.com", "user1", "user");
        userService.registerUser("user2@mail.com", "user2", "user");

        topicService.addTopic(userService.getByEmail("admin@mail.com"), "Тема 1", "Тестовий допис до теми 1", date.toString());
        topicService.addTopic(userService.getByEmail("admin@mail.com"), "Тема 2", "Тестовий допис до теми 2", date.toString());
        topicService.addTopic(userService.getByEmail("admin@mail.com"), "Тема 3", "Тестовий допис до теми 3", date.toString());

        postService.addPost(topicService.getById(0), userService.getByEmail("user1@mail.com"), date.toString(), "Це допис до теми");
        postService.addPost(topicService.getById(1), userService.getByEmail("user1@mail.com"), date.toString(), "І це допис");
        postService.addPost(topicService.getById(2), userService.getByEmail("user1@mail.com"), date.toString(), "І це також допис");

        postService.addPost(topicService.getById(0), userService.getByEmail("user2@mail.com"), date.toString(), "Це допис до теми");
        postService.addPost(topicService.getById(1), userService.getByEmail("user2@mail.com"), date.toString(), "І це допис");
        postService.addPost(topicService.getById(2), userService.getByEmail("user2@mail.com"), date.toString(), "І це також допис");

    }
}
