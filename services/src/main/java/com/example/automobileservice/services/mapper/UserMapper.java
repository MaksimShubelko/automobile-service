package com.example.automobileservice.services.mapper;

import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.services.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface UserMapper {

    UserDto mapToDto(UserEntity userEntity);

    UserEntity mapDtoToEntity(UserDto userDto);
}
