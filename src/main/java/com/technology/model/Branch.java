package com.technology.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Iwk0 on 13/03/2015.
 */
@Entity
@XmlRootElement
public class Branch extends ParentEntity {

    @XmlElement(name = "ProductURL")
    @Transient
    @Getter
    @Setter
    public String productURL;
    @Column
    private String name;
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "companyId")
    private Company company;
    @XmlElement(name = "Product")
    @Transient
    @Getter
    @Setter
    private String product;
    @XmlElement(name = "APR")
    @Transient
    @Getter
    @Setter
    private double apr;
    @XmlElement(name = "Currency")
    @Transient
    @Getter
    @Setter
    private String currency;
    @XmlElement(name = "MonthlyPayment")
    @Transient
    @Getter
    @Setter
    private double monthlyPayment;
    @XmlElement(name = "TotalPayed")
    @Transient
    @Getter
    @Setter
    private double totalPayed;
    @XmlElement(name = "InterestRateType")
    @Transient
    @Getter
    @Setter
    private String interestRateType;
    @XmlElement(name = "Bank")
    @Transient
    @Getter
    @Setter
    private String bank;
}