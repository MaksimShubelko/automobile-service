package com.example.automobileservice.services.mapper;

import com.example.automobileservice.entity.Amenity;
import com.example.automobileservice.entity.Request;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface AmenityMapper {

    AmenityDto mapToDto(Amenity amenity);

    Amenity mapDtoToEntity(AmenityDto amenityDto);
}
