package com.ars.web.services;

import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.models.form.IncomeSpendingForm;
import com.ars.web.repositories.FinancialInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialInfoServiceImpl implements FinancialInfoService {

    private final FinancialInfoRepository financialInfoRepository;

    public FinancialInfoServiceImpl(FinancialInfoRepository financialInfoRepository) {
        this.financialInfoRepository = financialInfoRepository;
    }

    @Override
    public FinancialInfo createOrUpdateIncomeSpending(Long id, IncomeSpendingForm form) {
        FinancialInfo financialInfo;

        if (id != null) {
            // Update existing record
            Optional<FinancialInfo> existing = financialInfoRepository.findById(id);
            financialInfo = existing.orElseGet(FinancialInfo::new);
        } else {
            // New record
            financialInfo = new FinancialInfo();
        }

        financialInfo.setIncome(form.getIncome());
        financialInfo.setSpending(form.getSpending());

        return financialInfoRepository.save(financialInfo);
    }

    @Override
    public FinancialInfo getFinancialInfo(Long id) {
        return financialInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Financial Info not found for ID: " + id));
    }

    @Override
    public FinancialInfo save(FinancialInfo financialInfo) {
        return financialInfoRepository.save(financialInfo);
    }

}
