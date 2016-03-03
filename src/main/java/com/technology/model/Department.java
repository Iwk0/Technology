package com.technology.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Department extends ParentEntity {

    @Getter @Setter private String name;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "department")
    @Getter @Setter private Set<Employee> employees;
}