package com.ars.web.controller;

import com.ars.web.models.form.IncomeSpendingForm;
import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.services.FinancialInfoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/income-spending")
public class IncomeSpendingController {

    private final FinancialInfoService financialInfoService;

    public IncomeSpendingController(FinancialInfoService financialInfoService) {
        this.financialInfoService = financialInfoService;
    }

    @GetMapping
    public String showIncomeSpendingForm(Model model) {
        model.addAttribute("incomeSpendingForm", new IncomeSpendingForm());
        return "income-spending";  // thymeleaf page income-spending.html
    }

    @PostMapping
    public String submitIncomeSpendingForm(
            @ModelAttribute("incomeSpendingForm") @Valid IncomeSpendingForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // If errors, stay on the same page
            return "income-spending";
        }

        FinancialInfo financialInfo = financialInfoService.createOrUpdateIncomeSpending(null, form);

        return "redirect:/government-contract?id=" + financialInfo.getId();
    }
}
