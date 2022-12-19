package com.example.automobileservice.services;

import com.example.automobileservice.services.dto.UserDto;

import java.util.List;

public interface UserService extends CrudService<UserDto, Long> {

    default boolean existsByLogin(String login) {
        throw new UnsupportedOperationException();
    }

    default UserDto findByLogin(String login) {
        throw new UnsupportedOperationException();
    }
}
