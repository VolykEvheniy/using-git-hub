package com.company.forum.database;

import com.company.forum.model.Topic;
import com.company.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Database {

    private Map<String, User> users;
    private List<Topic> topics;

    public Database() {
        users = new TreeMap<>();
        topics = new ArrayList<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

}
