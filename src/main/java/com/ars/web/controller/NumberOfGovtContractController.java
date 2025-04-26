package com.ars.web.controller;

import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.models.form.GovernmentContractForm;
import com.ars.web.services.FinancialInfoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/government-contract")
public class NumberOfGovtContractController {

    private final FinancialInfoService financialInfoService;

    public NumberOfGovtContractController(FinancialInfoService financialInfoService) {
        this.financialInfoService = financialInfoService;
    }

    @GetMapping
    public String showGovernmentContractForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("governmentContractForm", new GovernmentContractForm());
        model.addAttribute("id", id); // Pass the ID to the template
        return "government-contract";
    }

    @PostMapping
    public String submitGovernmentContractForm(
            @Valid @ModelAttribute("governmentContractForm") GovernmentContractForm form,
            BindingResult bindingResult,
            @RequestParam("id") Long id,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id); // Important! Re-pass id on error
            return "government-contract";
        }

        FinancialInfo financialInfo = financialInfoService.getFinancialInfo(id);
        financialInfo.setNumberOfContracts(form.getNumberOfContracts());
        financialInfoService.save(financialInfo); // <-- easy and correct!

        return "redirect:/government-contract-value?id=" + financialInfo.getId();
    }
}
