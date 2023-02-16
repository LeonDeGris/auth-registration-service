package com.bmstu.flowrence.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = {"users"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team extends BaseEntity {

    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_teams")
    Set<User> users;

    @OneToMany(mappedBy="owner")
    Set<Dashboard> dashboards;
}
