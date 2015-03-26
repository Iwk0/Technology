package com.technology.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Iwk0 on 13/03/2015.
 */
@Entity
public class Company extends ParentEntity {

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
    private List<Branch> branches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}