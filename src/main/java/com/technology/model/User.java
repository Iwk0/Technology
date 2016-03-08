package com.technology.model;

import com.google.gson.annotations.Expose;
import com.technology.util.validation.NotSame;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 9:58
 */
@Indexed
@Entity
public class User extends ParentEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Expose
    @Column
    @Size(min = 4)
    @NotSame
    @Getter
    @Setter
    @NotBlank
    private String username;

    @Column
    @Getter
    @Setter
    @NotBlank
    private String password;

    @Expose
    @Column
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private Status status = Status.ACTIVE;

    @Expose
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private Role role = Role.USER;

    public enum Role {
        ANONYMOUS, ADMIN, USER
    }
}