package com.ars.web.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class FinancialInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example fields
    private java.math.BigDecimal income;
    private java.math.BigDecimal spending;

    // number of contract page
    private Integer numberOfContracts;



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.math.BigDecimal getIncome() {
        return income;
    }

    public void setIncome(java.math.BigDecimal income) {
        this.income = income;
    }

    public java.math.BigDecimal getSpending() {
        return spending;
    }

    public void setSpending(java.math.BigDecimal spending) {
        this.spending = spending;
    }

    // Add getter and setter
    public Integer getNumberOfContracts() {
        return numberOfContracts;
    }

    public void setNumberOfContracts(Integer numberOfContracts) {
        this.numberOfContracts = numberOfContracts;
    }
}
