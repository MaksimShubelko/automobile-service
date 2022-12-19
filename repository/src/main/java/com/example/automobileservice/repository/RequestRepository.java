package com.example.automobileservice.repository;

import com.example.automobileservice.entity.Amenity;
import com.example.automobileservice.entity.Request;
import com.example.automobileservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Request findByUser(UserEntity user);


    List<Request> findByAmenity(Amenity amenity);
}
