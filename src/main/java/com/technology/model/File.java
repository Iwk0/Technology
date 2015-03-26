package com.technology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Blob;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-24
 * Time: 16:42
 */
@Entity
public class File extends ParentEntity {

    @Column
    private Blob file;

    @Column
    private String name;

    @Column
    private String contentType;

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}