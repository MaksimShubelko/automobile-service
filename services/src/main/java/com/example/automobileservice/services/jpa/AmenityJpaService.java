package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.Amenity;
import com.example.automobileservice.entity.Request;
import com.example.automobileservice.repository.AmenityRepository;
import com.example.automobileservice.services.AmenityService;
import com.example.automobileservice.services.RequestService;
import com.example.automobileservice.services.config.JpaImplementation;
import com.example.automobileservice.services.dto.AmenityDto;
import com.example.automobileservice.services.dto.RequestDto;
import com.example.automobileservice.services.mapper.AmenityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@JpaImplementation
public class AmenityJpaService extends AbstractJpaService<AmenityDto, Amenity, Long> implements AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    @Override
    public JpaRepository<Amenity, Long> getRepository() {
        return amenityRepository;
    }

    @Override
    public AmenityDto mapToDto(Amenity entity) {
        return amenityMapper.mapToDto(entity);
    }

    @Override
    public Amenity mapToEntity(AmenityDto dto) {
        return amenityMapper.mapDtoToEntity(dto);
    }
}
