package com.example.automobileservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    GUEST("Guest"),
    ADMINISTRATOR("Administrator"),
    CLIENT("Client");

    private static final Map<String, UserRole> MAP = Arrays.stream(UserRole.values())
            .collect(Collectors.toMap(UserRole::getValue, Function.identity()));

    private final String value;

    public String value() {
        return value;
    }

    public static UserRole getByValue(String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException();
        }

        UserRole role = MAP.get(value);
        return Objects.isNull(role) ? GUEST : role;
    }
}
