package com.tender.repository;

import com.tender.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

    List<Tender> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Tender> findByIndustryTypeContainingIgnoreCase(String industryType);

    List<Tender> findByBrokerInvolvementContainingIgnoreCase(String brokerInvolvement);

    @Query("SELECT t.industryType, COUNT(t) FROM Tender t GROUP BY t.industryType")
    List<Object[]> countByIndustryType();

    @Query("SELECT t.brokerInvolvement, COUNT(t) FROM Tender t GROUP BY t.brokerInvolvement")
    List<Object[]> countByBrokerInvolvement();

    @Query("SELECT t.afterCallRemarks, COUNT(t) FROM Tender t GROUP BY t.afterCallRemarks")
    List<Object[]> countByAfterCallRemarks();
}
