package com.example.application.dao.impl;

import com.example.application.dao.UserDao;
import com.example.application.entity.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appform.dropwizard.sharding.dao.LookupDao;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Singleton
public class UserDaoImpl implements UserDao {

    private final LookupDao<User> userDao;

    @Inject
    public UserDaoImpl(LookupDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(String userId) throws Exception {
        final Optional<User> user = userDao.get(userId);
        return user.orElse(null);
    }
}
