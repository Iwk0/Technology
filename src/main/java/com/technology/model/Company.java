package com.technology.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Iwk0 on 13/03/2015.
 */
@Entity
public class Company extends ParentEntity {

    @Column
    @Getter
    @Setter
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
    @Getter
    @Setter
    private List<Branch> branches;
}