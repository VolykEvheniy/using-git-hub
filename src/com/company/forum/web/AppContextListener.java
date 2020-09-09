package com.company.forum.web;

import com.company.forum.database.Database;
import com.company.forum.services.*;
import com.company.forum.testData.TestData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Database database = new Database();

        UserService userService = new UserServiceImpl(database);
        sce.getServletContext().setAttribute("userService", userService);

        PostService postService = new PostServiceImpl(database);
        sce.getServletContext().setAttribute("postService", postService);

        TopicService topicService = new TopicServiceImpl(database, postService);
        sce.getServletContext().setAttribute("topicService", topicService);

        TestData.addTestData(userService, topicService, postService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
