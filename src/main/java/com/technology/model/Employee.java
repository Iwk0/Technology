package com.technology.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@EqualsAndHashCode(exclude={"firstName", "lastName", "department"}, callSuper = false)
public class Employee extends ParentEntity {

    @Column(columnDefinition = "VARBINARY(500)", name = "firstName")
    @ColumnTransformer(
            read="AES_DECRYPT(firstName, 'DASJK43hEQWE')",
            write="AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter @Setter private String firstName;

    @Column(columnDefinition = "VARBINARY(500)", name = "lastName")
    @ColumnTransformer(
            read="AES_DECRYPT(lastName, 'DASJK43hEQWE')",
            write="AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter @Setter private String lastName;

    @Column(columnDefinition = "VARBINARY(500)", name = "EGN")
    @ColumnTransformer(
            read="AES_DECRYPT(EGN, 'DASJK43hEQWE')",
            write="AES_ENCRYPT(?, 'DASJK43hEQWE')")
    @Getter @Setter private String EGN;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    @Getter @Setter private Department department;
}