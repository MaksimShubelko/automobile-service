package com.example.automobileservice.services.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Service
@ConditionalOnProperty(name = "jpa.enable", havingValue = "true")
public @interface JpaImplementation {
}