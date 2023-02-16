package com.bmstu.flowrence.repository;

import com.bmstu.flowrence.entity.Task;
import com.bmstu.flowrence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends BaseEntityRepository<Task> {

    @Query("select max(task.simpleIdentifierNumber) from Task as task " +
            "where task.simpleIdentifierPrefix = :simpleIdentifierPrefix")
    Optional<Long> findMaxSimpleIdentifierNumber(@Param("simpleIdentifierPrefix") String simpleIdentifierPrefix);

    List<Task> findAllByAssignee(User assignee);

    List<Task> findAllByReporter(User reporter);
}
