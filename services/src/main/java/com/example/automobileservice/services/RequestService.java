package com.example.automobileservice.services;

import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.dto.UserDto;

import java.util.List;

public interface RequestService extends CrudService<RequestDto, Long> {

    default RequestDto findRequestByUser(UserDto userDto) {
        throw new UnsupportedOperationException();
    }

    default List<RequestDto> findRequestsByAmenity(AmenityDto amenityDto) {
        throw new UnsupportedOperationException();
    }
}
