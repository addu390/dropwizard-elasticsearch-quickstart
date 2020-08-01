package com.example.service;

import com.example.dto.UserDto;

public interface UserService {

    UserDto get(String userId) throws Exception;
}
