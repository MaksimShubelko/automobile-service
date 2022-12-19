package com.example.automobileservice.services.dto;

import com.example.automobileservice.entity.Amenity;
import com.example.automobileservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RequestDto {

    private Long id;

    private AmenityDto amenityDto;

    private UserDto userDto;
}
