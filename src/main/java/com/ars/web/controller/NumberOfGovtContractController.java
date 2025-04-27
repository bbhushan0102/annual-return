package com.ars.web.controller;

import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.models.form.GovernmentContractForm;
import com.ars.web.services.FinancialInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/government-contract")
@SessionAttributes("progressStatus")
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
            @ModelAttribute("progressStatus") Map<String, String> progressStatus, // Access progressStatus
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id); // Important! Re-pass id on error

            return "government-contract";
        }

        // Save the number of contracts in the financial info
        FinancialInfo financialInfo = financialInfoService.getFinancialInfo(id);
        financialInfo.setNumberOfContracts(form.getNumberOfContracts());
        financialInfoService.save(financialInfo); // Save the updated financial info

        // Update the progress status
        progressStatus.put("governmentContract", "completed");
        progressStatus.put("governmentContractValue", "current");

        // Redirect to the next page with updated progress status
        model.addAttribute("progressStatus", progressStatus); // Ensure the updated progress status is in the model
        return "redirect:/government-contract-value?id=" + financialInfo.getId();
    }
}
