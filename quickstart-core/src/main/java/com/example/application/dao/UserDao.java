package com.example.application.dao;

import com.example.application.entity.User;

public interface UserDao {

    User get(String userId) throws Exception;
}
