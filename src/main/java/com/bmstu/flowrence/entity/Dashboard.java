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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dashboard extends BaseEntity {

    String name;
    String description;
    String prefix;

    @OneToMany(mappedBy="dashboard")
    List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_team_uuid", nullable=false)
    Team owner;
}
