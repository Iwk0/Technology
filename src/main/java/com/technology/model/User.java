package com.technology.model;

import com.google.gson.annotations.Expose;
import com.technology.util.validation.BulstradDistributor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 9:58
 */
@Entity
public class User extends ParentEntity {

    public enum Role {
        ANONYMOUS, ADMIN, USER
    }

    @Expose
    @Column
    @NotBlank
    private String username;

    @Column
    private String password;

//    @BulstradDistributor
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Expose
    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Expose
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}