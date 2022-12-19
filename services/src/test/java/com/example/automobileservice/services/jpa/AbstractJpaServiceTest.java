package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.BaseEntity;
import com.example.automobileservice.entity.UserEntity;
import com.example.automobileservice.repository.UserRepository;
import com.example.automobileservice.services.BaseJpaService;
import com.example.automobileservice.services.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractJpaServiceTest {



    @Test
    void findById() {
        AbstractJpaService abstractJpaService =
                mock(AbstractJpaService.class, CALLS_REAL_METHODS);
        UserRepository jpaRepository = mock(UserRepository.class);
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        UserDto userDto = UserDto.builder().id(1L).build();
        Optional<UserEntity> optionalUser = Optional.of(userEntity);

        when(abstractJpaService.mapToDto(optionalUser.get())).thenReturn(userDto);
        when(abstractJpaService.getRepository()).thenReturn(jpaRepository);
        when(jpaRepository.findById(1L)).thenReturn(optionalUser);


        UserDto result = (UserDto) abstractJpaService.findById(1L);

        assertEquals(result, userDto);
    }

    @Test
    void save() {
        AbstractJpaService abstractJpaService =
                        mock(AbstractJpaService.class, CALLS_REAL_METHODS);
        UserRepository jpaRepository = mock(UserRepository.class);
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        UserDto userDto = UserDto.builder().id(1L).build();

        when(abstractJpaService.getRepository()).thenReturn(jpaRepository);
        when(abstractJpaService.mapToEntity(userDto)).thenReturn(userEntity);


        abstractJpaService.save(userDto);

        verify(jpaRepository, times(1)).save(userEntity);

    }

    @Test
    void findAll() {
        AbstractJpaService abstractJpaService =
                mock(AbstractJpaService.class, CALLS_REAL_METHODS);
        UserRepository jpaRepository = mock(UserRepository.class);
        UserEntity userEntity = UserEntity.builder().id(1L).build();
        UserDto userDto = UserDto.builder().id(1L).build();
        List<UserEntity> userEntityList = new ArrayList<>(List.of(userEntity));
        List<UserDto> userDtos = new ArrayList<>(List.of(userDto));

        when(abstractJpaService.mapToDto(userEntity)).thenReturn(userDto);
        when(abstractJpaService.getRepository()).thenReturn(jpaRepository);
        when(jpaRepository.findAll()).thenReturn(userEntityList);


        List<UserDto> result = (List<UserDto>) abstractJpaService.findAll();

        assertEquals(result, userDtos);
    }

    @Test
    void delete() {
        AbstractJpaService abstractJpaService =
                mock(AbstractJpaService.class, CALLS_REAL_METHODS);
        UserRepository jpaRepository = mock(UserRepository.class);

        when(abstractJpaService.getRepository()).thenReturn(jpaRepository);

        abstractJpaService.delete(1L);

        verify(jpaRepository, times(1)).deleteById(1L);
    }
}