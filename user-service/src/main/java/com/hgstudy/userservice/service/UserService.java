package com.hgstudy.userservice.service;

import com.hgstudy.userservice.dto.UserDto;
import com.hgstudy.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
