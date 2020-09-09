package com.company.forum.services;

import com.company.forum.model.Topic;
import com.company.forum.model.User;

import java.util.List;


public interface TopicService {

    void addTopic(User author, String title, String topicText, String date);

    void editTopic(int topicId, String title, String topicText);

    void removeTopic(int topicId);

    Topic getById(int topicId);

    Integer countOfPosts(int topicId);

    List<Topic> getAllTopics();
}
