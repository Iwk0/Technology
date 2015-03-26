package com.technology.model;

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

    @Column
    private String name;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "companyId")
    private Company company;

    @XmlElement(name = "Product")
    @Transient
    private String product;

    @XmlElement(name = "APR")
    @Transient
    private double apr;

    @XmlElement(name = "Currency")
    @Transient
    private String currency;

    @XmlElement(name = "MonthlyPayment")
    @Transient
    private double monthlyPayment;

    @XmlElement(name = "TotalPayed")
    @Transient
    private double totalPayed;

    @XmlElement(name = "InterestRateType")
    @Transient
    private String interestRateType;

    @XmlElement(name = "Bank")
    @Transient
    private String bank;

    @XmlElement(name = "ProductURL")
    @Transient
    public String productURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getTotalPayed() {
        return totalPayed;
    }

    public void setTotalPayed(double totalPayed) {
        this.totalPayed = totalPayed;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }
}