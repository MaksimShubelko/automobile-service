package com.example.automobileservice.converter;

import com.example.automobileservice.entity.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        if (Objects.isNull(attribute)) {
            attribute = UserRole.CLIENT;
        }
        return attribute.getValue();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.getByValue(dbData);
    }
}