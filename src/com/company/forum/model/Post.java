package com.company.forum.model;

public class Post {

    Integer postId;
    Topic topic;
    User author;
    String date;
    String postText;

    public Post(Topic topic, User author, String date, String postText) {
        this.topic = topic;
        this.author = author;
        this.date = date;
        this.postText = postText;
    }

    public Topic getTopic() {
        return topic;
    }

    public Integer getPostId() {
        return postId;
    }

    public User getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getPostText() {
        return postText;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
