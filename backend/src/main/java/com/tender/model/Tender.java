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

    @Column(name = "email")
    private String email;

    @Column(name = "broker_involvement")
    private String brokerInvolvement;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "after_call_remarks", columnDefinition = "TEXT")
    private String afterCallRemarks;

    // Constructors
    public Tender() {}

    public Tender(String companyName, String industryType, String headOfficeAddress,
                  String ncrOfficeLocation, String contactPerson, String designation,
                  String phoneNo, String email, String brokerInvolvement,
                  String remarks, String afterCallRemarks) {
        this.companyName = companyName;
        this.industryType = industryType;
        this.headOfficeAddress = headOfficeAddress;
        this.ncrOfficeLocation = ncrOfficeLocation;
        this.contactPerson = contactPerson;
        this.designation = designation;
        this.phoneNo = phoneNo;
        this.email = email;
        this.brokerInvolvement = brokerInvolvement;
        this.remarks = remarks;
        this.afterCallRemarks = afterCallRemarks;
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

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBrokerInvolvement() { return brokerInvolvement; }
    public void setBrokerInvolvement(String brokerInvolvement) { this.brokerInvolvement = brokerInvolvement; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getAfterCallRemarks() { return afterCallRemarks; }
    public void setAfterCallRemarks(String afterCallRemarks) { this.afterCallRemarks = afterCallRemarks; }
}
