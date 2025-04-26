package com.ars.web.models.form;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class IncomeSpendingForm {

    @NotNull(message = "Income is required")
    @Digits(integer = 12, fraction = 2, message = "Income must be a valid monetary value")
    @DecimalMin(value = "0.00", inclusive = true, message = "Income must be at least 0")
    private BigDecimal income;

    @NotNull(message = "Spending is required")
    @Digits(integer = 12, fraction = 2, message = "Spending must be a valid monetary value")
    @DecimalMin(value = "0.00", inclusive = true, message = "Spending must be at least 0")
    private BigDecimal spending;

    // Getters and Setters
    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getSpending() {
        return spending;
    }

    public void setSpending(BigDecimal spending) {
        this.spending = spending;
    }
}
