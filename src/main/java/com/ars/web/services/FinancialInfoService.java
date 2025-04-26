package com.ars.web.services;

import com.ars.web.models.entity.FinancialInfo;



import com.ars.web.models.entity.FinancialInfo;
import com.ars.web.models.form.IncomeSpendingForm;

public interface FinancialInfoService {
    FinancialInfo createOrUpdateIncomeSpending(Long id, IncomeSpendingForm form);
    FinancialInfo getFinancialInfo(Long id);

    FinancialInfo save(FinancialInfo financialInfo);
}


