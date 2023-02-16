package com.bmstu.flowrence.service;

import com.bmstu.flowrence.entity.BaseEntity;
import com.bmstu.flowrence.exception.ObjectNotFoundException;
import com.bmstu.flowrence.repository.BaseEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public abstract class AbstractEntityService <E extends BaseEntity, R extends BaseEntityRepository<E>> {

    protected R repository;

    public E retrieveByIdentifier(String uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new ObjectNotFoundException(uuid));
    }

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }
}
