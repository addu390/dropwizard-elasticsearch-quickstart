package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
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

    @Override
    public boolean save(final User user) throws Exception {
        Optional<User> result = userDao.save(user);
        return result.isPresent();
    }
}
