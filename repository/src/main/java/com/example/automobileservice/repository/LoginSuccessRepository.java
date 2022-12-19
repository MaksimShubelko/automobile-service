package com.example.automobileservice.repository;

import com.example.automobileservice.entity.LoginSuccess;
import com.example.automobileservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {

    Optional<LoginSuccess> findTopByUserOrderByLastModifiedDateDesc(UserEntity user);
}