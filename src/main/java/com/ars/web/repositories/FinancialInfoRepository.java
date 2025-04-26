package com.ars.web.repositories;

import com.ars.web.models.entity.FinancialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialInfoRepository extends JpaRepository<FinancialInfo, Long> {
}

