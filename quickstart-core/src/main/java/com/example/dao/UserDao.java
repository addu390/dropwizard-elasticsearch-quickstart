package com.example.dao;

import com.example.entity.User;

public interface UserDao {

    User get(String userId) throws Exception;

    boolean save(User user) throws Exception;
}
