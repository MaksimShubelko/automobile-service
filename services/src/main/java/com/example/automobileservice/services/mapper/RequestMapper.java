package com.example.automobileservice.services.mapper;

import com.example.automobileservice.entity.Request;
import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UserMapper.class, AmenityMapper.class})
public interface RequestMapper {

    @Mapping(target = "amenityDto", source = "amenity")
    @Mapping(target = "userDto", source = "user")
    RequestDto mapToDto(Request request);

    @Mapping(source = "amenityDto", target = "amenity")
    @Mapping(source = "userDto", target = "user")
    Request mapDtoToEntity(RequestDto requestDto);
}
