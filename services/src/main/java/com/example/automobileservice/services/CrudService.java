package com.example.automobileservice.services;

import java.util.Collection;
import java.util.List;

public interface CrudService<D, ID> {
    D findById(ID id);

    void save(D dto);

    List<D> findAll();

    void delete(ID id);
}
