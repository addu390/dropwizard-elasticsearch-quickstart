package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dto.UserDto;
import com.example.service.UserService;
import lombok.val;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto get(String userId) throws Exception {
        val user = userDao.get(userId);
        return UserDto.builder()
                .userId(user.getUserId())
                .build();
    }
}
