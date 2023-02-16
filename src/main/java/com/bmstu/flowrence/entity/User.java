package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = {"teams"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String firstName;
    String lastName;
//    TODO: add @UniqueConstraint
    String email;
    String password;
    Boolean active;

    @ManyToMany(mappedBy = "users")
    Set<Team> teams;

    @OneToMany(mappedBy="reporter")
    List<Task> reportedTasks;

    @OneToMany(mappedBy="assignee")
    List<Task> assignedTasks;
}
