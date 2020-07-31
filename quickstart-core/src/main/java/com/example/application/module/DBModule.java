package com.example.application.module;

import com.example.application.ServiceConfiguration;
import com.example.application.dao.UserDao;
import com.example.application.dao.impl.UserDaoImpl;
import com.example.application.entity.User;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appform.dropwizard.sharding.DBShardingBundle;
import io.appform.dropwizard.sharding.dao.LookupDao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DBModule extends AbstractModule {

    private final DBShardingBundle<ServiceConfiguration> dbShardingBundle;

    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoImpl.class);
    }

    @Provides
    @Singleton
    public LookupDao<User> provideUserDao() {
        return dbShardingBundle.createParentObjectDao(User.class);
    }
}
