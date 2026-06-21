package com.tender.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tenders")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "industry_type")
    private String industryType;

    @Column(name = "head_office_address", columnDefinition = "TEXT")
    private String headOfficeAddress;

    @Column(name = "ncr_office_location", columnDefinition = "TEXT")
    private String ncrOfficeLocation;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "designation")
    private String designation;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "tender_reference_no")
    private String tenderReferenceNo;

    @Column(name = "tender_status")
    private String tenderStatus;

    @Column(name = "type_of_insurance")
    private String typeOfInsurance;

    @Column(name = "broker_involvement")
    private String brokerInvolvement;

    @Column(name = "existing_broker")
    private String existingBroker;

    @Column(name = "opportunity_stage")
    private String opportunityStage;

    @Column(name = "last_contact_date")
    private String lastContactDate;

    @Column(name = "remarks_intel", columnDefinition = "TEXT")
    private String remarksIntel;

    @Column(name = "after_call_notes", columnDefinition = "TEXT")
    private String afterCallNotes;

    // Constructors
    public Tender() {}

    public Tender(String companyName, String industryType, String headOfficeAddress,
                  String ncrOfficeLocation, String contactPerson, String designation,
                  String phoneNo, String emailId, String tenderReferenceNo,
                  String tenderStatus, String typeOfInsurance, String brokerInvolvement,
                  String existingBroker, String opportunityStage, String lastContactDate,
                  String remarksIntel, String afterCallNotes) {
        this.companyName = companyName;
        this.industryType = industryType;
        this.headOfficeAddress = headOfficeAddress;
        this.ncrOfficeLocation = ncrOfficeLocation;
        this.contactPerson = contactPerson;
        this.designation = designation;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.tenderReferenceNo = tenderReferenceNo;
        this.tenderStatus = tenderStatus;
        this.typeOfInsurance = typeOfInsurance;
        this.brokerInvolvement = brokerInvolvement;
        this.existingBroker = existingBroker;
        this.opportunityStage = opportunityStage;
        this.lastContactDate = lastContactDate;
        this.remarksIntel = remarksIntel;
        this.afterCallNotes = afterCallNotes;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustryType() { return industryType; }
    public void setIndustryType(String industryType) { this.industryType = industryType; }

    public String getHeadOfficeAddress() { return headOfficeAddress; }
    public void setHeadOfficeAddress(String headOfficeAddress) { this.headOfficeAddress = headOfficeAddress; }

    public String getNcrOfficeLocation() { return ncrOfficeLocation; }
    public void setNcrOfficeLocation(String ncrOfficeLocation) { this.ncrOfficeLocation = ncrOfficeLocation; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getTenderReferenceNo() { return tenderReferenceNo; }
    public void setTenderReferenceNo(String tenderReferenceNo) { this.tenderReferenceNo = tenderReferenceNo; }

    public String getTenderStatus() { return tenderStatus; }
    public void setTenderStatus(String tenderStatus) { this.tenderStatus = tenderStatus; }

    public String getTypeOfInsurance() { return typeOfInsurance; }
    public void setTypeOfInsurance(String typeOfInsurance) { this.typeOfInsurance = typeOfInsurance; }

    public String getBrokerInvolvement() { return brokerInvolvement; }
    public void setBrokerInvolvement(String brokerInvolvement) { this.brokerInvolvement = brokerInvolvement; }

    public String getExistingBroker() { return existingBroker; }
    public void setExistingBroker(String existingBroker) { this.existingBroker = existingBroker; }

    public String getOpportunityStage() { return opportunityStage; }
    public void setOpportunityStage(String opportunityStage) { this.opportunityStage = opportunityStage; }

    public String getLastContactDate() { return lastContactDate; }
    public void setLastContactDate(String lastContactDate) { this.lastContactDate = lastContactDate; }

    public String getRemarksIntel() { return remarksIntel; }
    public void setRemarksIntel(String remarksIntel) { this.remarksIntel = remarksIntel; }

    public String getAfterCallNotes() { return afterCallNotes; }
    public void setAfterCallNotes(String afterCallNotes) { this.afterCallNotes = afterCallNotes; }
}
