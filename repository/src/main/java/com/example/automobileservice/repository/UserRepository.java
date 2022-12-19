package com.example.automobileservice.repository;

import com.example.automobileservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByLogin(String login);

    boolean existsByLogin(String login);
}
