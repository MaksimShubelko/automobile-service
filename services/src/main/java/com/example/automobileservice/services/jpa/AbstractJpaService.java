package com.example.automobileservice.services.jpa;

import com.example.automobileservice.entity.BaseEntity;
import com.example.automobileservice.services.BaseJpaService;
import com.example.automobileservice.services.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public abstract class AbstractJpaService<D, T extends BaseEntity<ID>, ID> implements CrudService<D, ID>, BaseJpaService<T, ID> {

    @Override
    public D findById(ID id) {
        return getRepository()
                .findById(id)
                .map(this::mapToDto)
                . orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public void save(D dto) {
        getRepository().save(mapToEntity(dto));

    }

    @Override
    public List<D> findAll() {
        return getRepository()
                .findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);

    }

    public abstract D mapToDto(T entity);

    public abstract T mapToEntity(D dto);
}
