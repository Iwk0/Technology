package com.technology.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 10:08
 */
@MappedSuperclass
public class ParentEntity implements Serializable {

    @Id
    @Expose
    @GeneratedValue
    private Long id;

    @Column
    private Date dateCreated;

    @Column
    private Date dateLastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @PreUpdate
    public void preUpdate(){
        this.dateLastModified = new Date();
    }

    @PrePersist
    public void perCreate(){
        this.dateCreated = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ParentEntity)) {
            return false;
        }

        ParentEntity that = (ParentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}