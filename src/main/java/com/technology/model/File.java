package com.technology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Blob;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-24
 * Time: 16:42
 */
@Entity
public class File extends ParentEntity {

    public enum ContentType {
        IMAGE, VIDEO, TEXT
    }

    @Column
    private Blob file;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private ContentType contentType = ContentType.TEXT;

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

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}