package com.company.forum.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    Integer topicId;
    User author;
    String title;
    String topicText;
    String date;
    List<Post> posts;

    public Topic(User author, String title, String topicText, String date) {
        this.author = author;
        this.title = title;
        this.topicText = topicText;
        this.date = date;
        this.posts = new ArrayList<>();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getTopicText() {
        return topicText;
    }

    public String getDate() {
        return date;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public void setDate(String date) { this.date = date; }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
