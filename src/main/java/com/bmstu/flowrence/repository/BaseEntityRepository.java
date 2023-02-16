package com.bmstu.flowrence.repository;

import com.bmstu.flowrence.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, String> {
}
