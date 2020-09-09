package com.company.forum.services;

import com.company.forum.model.Post;
import com.company.forum.model.Topic;
import com.company.forum.model.User;

public interface PostService {

    void addPost(Topic topic, User author, String date, String postText);

    void removePost(Topic topic, int postId);

    Post getById(Topic topic, int postId);

}
