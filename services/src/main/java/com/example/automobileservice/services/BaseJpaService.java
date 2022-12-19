package com.example.automobileservice.services;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseJpaService<T, ID> {

    JpaRepository<T, ID> getRepository();
}
