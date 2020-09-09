package com.company.forum.services;

import com.company.forum.database.Database;
import com.company.forum.model.Post;
import com.company.forum.model.Topic;
import com.company.forum.model.User;

import java.util.List;

public class TopicServiceImpl implements TopicService{

    Database database;
    PostService postService;

    public TopicServiceImpl(Database database, PostService postService) {
        this.database = database;
        this.postService = postService;
    }

    @Override
    public void addTopic(User author, String title, String topicText, String date) {
        List<Topic> topics = database.getTopics();
        Topic topic = new Topic(author, title, topicText, date);
        topics.add(topic);
        int topicId = topics.indexOf(topic);
        topics.get(topicId).setTopicId(topicId);
        postService.addPost(topic, database.getUsers().get("admin@mail.com"), date, topicText);
    }

    @Override
    public void editTopic(int topicId, String title, String topicText) {
        List<Topic> topics = database.getTopics();
        Topic topic = topics.get(topicId);
        topic.setTitle(title);
        topic.setTopicText(topicText);
        List<Post> posts = topic.getPosts();
        posts.get(0).setPostText(topicText);
    }

    @Override
    public void removeTopic(int topicId) {
        List<Topic> topics = database.getTopics();
        topics.remove(topicId);
        for (Topic t : topics) {
            t.setTopicId(topics.indexOf(t));
        }
    }

    @Override
    public Topic getById(int topicId) {
        return database.getTopics().get(topicId);
    }

    @Override
    public Integer countOfPosts(int topicId) {
        Integer result = database.getTopics().get(topicId).getPosts().size();
        return result;
    }

    @Override
    public List<Topic> getAllTopics() {
        return database.getTopics();
    }
}
