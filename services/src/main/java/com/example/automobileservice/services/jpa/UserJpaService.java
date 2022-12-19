package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.services.UserService;
import com.example.automobileservice.services.config.JpaImplementation;
import com.example.automobileservice.services.dto.UserDto;
import com.example.automobileservice.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@JpaImplementation
public class UserJpaService extends AbstractJpaService<UserDto, UserEntity, Long> implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDto findByLogin(String login) {
        return mapToDto(repository.findUserEntityByLogin(login).orElseThrow());
    }

    @Override
    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public JpaRepository<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public UserDto mapToDto(UserEntity entity) {
        return userMapper.mapToDto(entity);
    }

    @Override
    public UserEntity mapToEntity(UserDto dto) {
        return userMapper.mapDtoToEntity(dto);
    }
}
