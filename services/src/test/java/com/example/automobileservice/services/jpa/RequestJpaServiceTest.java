package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.Amenity;
import com.example.automobileservice.entity.Request;
import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.RequestRepository;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.dto.UserDto;
import com.example.automobileservice.services.mapper.AmenityMapper;
import com.example.automobileservice.services.mapper.RequestMapper;
import com.example.automobileservice.services.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestJpaServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RequestRepository repository;

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private AmenityMapper amenityMapper;

    @InjectMocks
    private RequestJpaService requestJpaService;


    @Test
    void findRequestByUserFail() {
        UserDto userDto = mock(UserDto.class);
        UserEntity user = mock(UserEntity.class);
        Request request = null;
        RequestDto requestDto = mock(RequestDto.class);

        when(userMapper.mapDtoToEntity(userDto)).thenReturn(user);
        when(repository.findByUser(user)).thenReturn(request);

        RequestDto result = requestJpaService.findRequestByUser(userDto);

        assertNotNull(result);
        verify(requestMapper, times(0)).mapToDto(request);
    }

    @Test
    void findRequestByUserSuccess() {
        UserDto userDto = mock(UserDto.class);
        UserEntity user = mock(UserEntity.class);
        Request request = new Request();
        RequestDto requestDto = mock(RequestDto.class);

        when(userMapper.mapDtoToEntity(userDto)).thenReturn(user);
        when(repository.findByUser(user)).thenReturn(request);
        when(requestMapper.mapToDto(request)).thenReturn(requestDto);

        RequestDto result = requestJpaService.findRequestByUser(userDto);

        assertNotNull(result);
        verify(requestMapper, times(1)).mapToDto(request);
    }

    @Test
    void findRequestsByAmenity() {
        AmenityDto amenityDto = mock(AmenityDto.class);
        Amenity amenity = new Amenity();
        Request request = new Request();
        RequestDto requestDto = RequestDto.builder().build();
        List<RequestDto> requestDtos = new ArrayList<>(List.of(requestDto));
        List<Request> requests = new ArrayList<>(List.of(request));

        when(amenityMapper.mapDtoToEntity(amenityDto)).thenReturn(amenity);
        when(repository.findByAmenity(amenity)).thenReturn(requests);
        when(requestMapper.mapToDto(request)).thenReturn(requestDto);

        List<RequestDto> result = requestJpaService.findRequestsByAmenity(amenityDto);

        assertEquals(requestDtos, result);

    }

}