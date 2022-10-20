package com.smartbuy.Smart.Buy.dao;

import com.smartbuy.Smart.Buy.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();
    void deleteUser(Long id);
    void createUser(User user);
    User obtainUserCredentials(User user);
}
