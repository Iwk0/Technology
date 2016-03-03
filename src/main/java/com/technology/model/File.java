package com.technology.model;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    private Blob file;

    @Column
    @Expose
    @Getter
    @Setter
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ContentType contentType = ContentType.TEXT;
}