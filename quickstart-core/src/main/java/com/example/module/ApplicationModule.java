package com.example.module;

import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
