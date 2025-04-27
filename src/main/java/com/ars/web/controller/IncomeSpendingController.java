package com.ars.web.controller;

import com.ars.web.models.form.IncomeSpendingForm;
import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.services.FinancialInfoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/income-spending")
@SessionAttributes("progressStatus")
public class IncomeSpendingController {

    private final FinancialInfoService financialInfoService;

    public IncomeSpendingController(FinancialInfoService financialInfoService) {
        this.financialInfoService = financialInfoService;
    }

    @ModelAttribute("progressStatus")
    public Map<String, String> progressStatus() {
        return new HashMap<>(Map.of(
                "incomeSpending", "notstarted",
                "governmentContract", "notstarted",
                "governmentContractValue", "notstarted"
        ));
    }

    @GetMapping
    public String showIncomeSpendingForm(Model model) {
        model.addAttribute("incomeSpendingForm", new IncomeSpendingForm());
        Map<String, String> progressStatus = (Map<String, String>) model.getAttribute("progressStatus");
        if (progressStatus == null) {
            progressStatus = new HashMap<>();
        }
        progressStatus.put("incomeSpending", "current");
        model.addAttribute("progressStatus", progressStatus);
        return "income-spending"; // Thymeleaf page for the income-spending form
    }

    @PostMapping
    public String submitIncomeSpendingForm(
            @ModelAttribute("incomeSpendingForm") @Valid IncomeSpendingForm form,
            BindingResult bindingResult,
            @ModelAttribute("progressStatus") Map<String, String> progressStatus,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "income-spending"; // return to the form page if errors
        }

        // Save the form data using the service
        FinancialInfo financialInfo = financialInfoService.createOrUpdateIncomeSpending(null, form);

        // Update the progress status
        progressStatus.put("incomeSpending", "completed");
        progressStatus.put("governmentContract", "current");

        // Add the updated progress status to the session via model
        model.addAttribute("progressStatus", progressStatus);

        return "redirect:/government-contract?id=" + financialInfo.getId();
    }
}