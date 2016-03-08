package com.technology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Indexed
@Entity
@EqualsAndHashCode(exclude = {"firstName", "lastName", "department"}, callSuper = false)
public class Employee extends ParentEntity {

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(columnDefinition = "TINYBLOB", name = "firstName")
    @ColumnTransformer(
            read = "AES_DECRYPT(firstName, 'DASJK43hEQWE')",
            write = "AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter
    @Setter
    @NotBlank
    private String firstName;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(columnDefinition = "TINYBLOB", name = "lastName")
    @ColumnTransformer(
            read = "AES_DECRYPT(lastName, 'DASJK43hEQWE')",
            write = "AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter
    @Setter
    @NotBlank
    private String lastName;

    @Column(columnDefinition = "TINYBLOB", name = "EGN")
    @ColumnTransformer(
            read = "AES_DECRYPT(EGN, 'DASJK43hEQWE')",
            write = "AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter
    @Setter
    @NotBlank
    private String egn;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    @Getter
    @Setter
    private Department department;
}