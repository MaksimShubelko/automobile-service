package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.services.dto.UserDto;
import com.example.automobileservice.services.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJpaServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserJpaService userJpaService;

    @Test
    void findByLogin() {
        String login = "login";
        UserEntity user = new UserEntity();
        UserDto userDto = UserDto.builder().build();
        Optional<UserEntity> userEntityOptional = Optional.of(user);

        when(repository.findUserEntityByLogin(login)).thenReturn(userEntityOptional);
        when(userMapper.mapToDto(user)).thenReturn(userDto);

        UserDto result = userJpaService.findByLogin(login);

        assertEquals(userDto, result);

    }

}