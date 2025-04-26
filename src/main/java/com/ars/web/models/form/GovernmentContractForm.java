package com.ars.web.models.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GovernmentContractForm {
    @NotNull(message = "Please enter the number of government contracts.")
    @Min(value = 0, message = "The number must be zero or more.")
    private Integer numberOfContracts;

    public Integer getNumberOfContracts() {
        return numberOfContracts;
    }

    public void setNumberOfContracts(Integer numberOfContracts) {
        this.numberOfContracts = numberOfContracts;
    }
}
