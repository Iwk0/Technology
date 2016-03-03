package com.technology.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Department extends ParentEntity {

    @Getter @Setter private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @Getter @Setter private Set<Employee> employees;
}