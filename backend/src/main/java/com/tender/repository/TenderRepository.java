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

    // Industry breakdown
    @Query("SELECT t.industryType, COUNT(t) FROM Tender t GROUP BY t.industryType")
    List<Object[]> countByIndustryType();

    // Broker involvement breakdown
    @Query("SELECT t.brokerInvolvement, COUNT(t) FROM Tender t GROUP BY t.brokerInvolvement")
    List<Object[]> countByBrokerInvolvement();

    // After call notes breakdown
    @Query("SELECT t.afterCallNotes, COUNT(t) FROM Tender t GROUP BY t.afterCallNotes")
    List<Object[]> countByAfterCallNotes();

    // Tender status breakdown
    @Query("SELECT t.tenderStatus, COUNT(t) FROM Tender t GROUP BY t.tenderStatus")
    List<Object[]> countByTenderStatus();

    // Type of insurance breakdown
    @Query("SELECT t.typeOfInsurance, COUNT(t) FROM Tender t GROUP BY t.typeOfInsurance")
    List<Object[]> countByTypeOfInsurance();

    // Existing broker breakdown
    @Query("SELECT t.existingBroker, COUNT(t) FROM Tender t GROUP BY t.existingBroker")
    List<Object[]> countByExistingBroker();

    // Opportunity stage breakdown
    @Query("SELECT t.opportunityStage, COUNT(t) FROM Tender t GROUP BY t.opportunityStage")
    List<Object[]> countByOpportunityStage();

    // Designation breakdown
    @Query("SELECT t.designation, COUNT(t) FROM Tender t GROUP BY t.designation")
    List<Object[]> countByDesignation();

    // NCR office location breakdown (for geographic analysis)
    @Query("SELECT t.ncrOfficeLocation, COUNT(t) FROM Tender t GROUP BY t.ncrOfficeLocation")
    List<Object[]> countByNcrOfficeLocation();
}
