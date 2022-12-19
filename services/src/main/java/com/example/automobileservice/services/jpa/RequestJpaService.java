package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.Request;
import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.RequestRepository;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.services.RequestService;
import com.example.automobileservice.services.UserService;
import com.example.automobileservice.services.config.JpaImplementation;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.dto.UserDto;
import com.example.automobileservice.services.mapper.AmenityMapper;
import com.example.automobileservice.services.mapper.RequestMapper;
import com.example.automobileservice.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@JpaImplementation
public class RequestJpaService extends AbstractJpaService<RequestDto, Request, Long> implements RequestService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RequestRepository repository;
    private final RequestMapper requestMapper;
    private final AmenityMapper amenityMapper;

    @Override
    public RequestDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public RequestDto findRequestByUser(UserDto userDto) {
        Request request = repository.findByUser(userMapper.mapDtoToEntity(userDto));
        if (Objects.isNull(request)) {
            return RequestDto.builder().build();
        } else {
            return mapToDto(request);
        }
    }

    @Override
    public List<RequestDto> findRequestsByAmenity(AmenityDto amenityDto) {
        return repository.findByAmenity(amenityMapper.mapDtoToEntity(amenityDto))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JpaRepository<Request, Long> getRepository() {
        return repository;
    }

    @Override
    public RequestDto mapToDto(Request entity) {
        return requestMapper.mapToDto(entity);
    }

    @Override
    public Request mapToEntity(RequestDto dto) {
        return requestMapper.mapDtoToEntity(dto);
    }
}
