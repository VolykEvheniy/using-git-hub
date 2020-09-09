package com.company.forum.services;

import com.company.forum.database.Database;
import com.company.forum.model.Post;
import com.company.forum.model.Topic;
import com.company.forum.model.User;

import java.util.List;

public class PostServiceImpl implements PostService {

    Database database;

    public PostServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addPost(Topic topic, User author, String date, String postText) {
        List<Post> posts = database.getTopics().get(topic.getTopicId()).getPosts();
        Post post = new Post(topic, author, date, postText);
        posts.add(post);
        int postId = posts.indexOf(post);
        posts.get(postId).setPostId(postId);
    }

    @Override
    public void removePost(Topic topic, int postId) {
        List<Post> posts = database.getTopics().get(topic.getTopicId()).getPosts();
        posts.remove(postId);
        for (Post p : posts) {
            p.setPostId(posts.indexOf(p));
        }
    }

    @Override
    public Post getById(Topic topic, int postId) {
        List<Post> posts = database.getTopics().get(topic.getTopicId()).getPosts();
        Post post = posts.get(postId);
        return post;
    }
}
