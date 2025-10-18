package com.projects.myauthapp.mvccontrollers.manager;

import com.projects.myauthapp.entities.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class UserManager {
    private static final int MAX_USERS = 35;
    private static final LinkedList<User> userList = new LinkedList<>();

    // Private constructor to prevent direct instantiation
    private UserManager() {}

    // Singleton-style access
    public static UserManager getUserManager() {
        return new UserManager();
    }

    // Add user with size constraint
    public void addUser(User user) {
        if (userList.size() >= MAX_USERS) {
            // remove first (oldest) user
            userList.removeFirst();
        }
        userList.addLast(user); // add new user
    }

    // Get all users
    public List<User> getAllUsers() {
        return new LinkedList<>(userList); // return copy for safety
    }

    // Get current count
    public int getUserCount() {
        return userList.size();
    }

    // Optional: clear all users
    public void clearUsers() {
        userList.clear();
    }
}
