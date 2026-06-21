package com.tender.service;

import com.tender.model.Tender;
import com.tender.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    public List<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }

    public Optional<Tender> getTenderById(Long id) {
        return tenderRepository.findById(id);
    }

    public Tender createTender(Tender tender) {
        return tenderRepository.save(tender);
    }

    public Tender updateTender(Long id, Tender tenderDetails) {
        Tender tender = tenderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tender not found with id: " + id));

        tender.setCompanyName(tenderDetails.getCompanyName());
        tender.setIndustryType(tenderDetails.getIndustryType());
        tender.setHeadOfficeAddress(tenderDetails.getHeadOfficeAddress());
        tender.setNcrOfficeLocation(tenderDetails.getNcrOfficeLocation());
        tender.setContactPerson(tenderDetails.getContactPerson());
        tender.setDesignation(tenderDetails.getDesignation());
        tender.setPhoneNo(tenderDetails.getPhoneNo());
        tender.setEmailId(tenderDetails.getEmailId());
        tender.setTenderReferenceNo(tenderDetails.getTenderReferenceNo());
        tender.setTenderStatus(tenderDetails.getTenderStatus());
        tender.setTypeOfInsurance(tenderDetails.getTypeOfInsurance());
        tender.setBrokerInvolvement(tenderDetails.getBrokerInvolvement());
        tender.setExistingBroker(tenderDetails.getExistingBroker());
        tender.setOpportunityStage(tenderDetails.getOpportunityStage());
        tender.setLastContactDate(tenderDetails.getLastContactDate());
        tender.setRemarksIntel(tenderDetails.getRemarksIntel());
        tender.setAfterCallNotes(tenderDetails.getAfterCallNotes());

        return tenderRepository.save(tender);
    }

    public void deleteTender(Long id) {
        Tender tender = tenderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tender not found with id: " + id));
        tenderRepository.delete(tender);
    }

    public List<Tender> searchTenders(String query) {
        return tenderRepository.findByCompanyNameContainingIgnoreCase(query);
    }

    public List<Tender> filterByIndustry(String industry) {
        return tenderRepository.findByIndustryTypeContainingIgnoreCase(industry);
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        long totalTenders = tenderRepository.count();
        stats.put("totalTenders", totalTenders);

        // ===== 1. Industry breakdown =====
        List<Object[]> industryData = tenderRepository.countByIndustryType();
        List<Map<String, Object>> industryBreakdown = industryData.stream()
                .map(row -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", row[0] != null ? ((String) row[0]).trim() : "Unknown");
                    item.put("count", row[1]);
                    return item;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("industryBreakdown", industryBreakdown);
        stats.put("totalIndustries", industryBreakdown.size());

        // ===== 2. Broker involvement breakdown =====
        List<Object[]> brokerData = tenderRepository.countByBrokerInvolvement();
        List<Map<String, Object>> brokerBreakdown = brokerData.stream()
                .map(row -> {
                    Map<String, Object> item = new HashMap<>();
                    String val = row[0] != null ? ((String) row[0]).trim() : "Not Specified";
                    if (val.isEmpty()) val = "Not Specified";
                    item.put("name", val);
                    item.put("count", row[1]);
                    return item;
                })
                .collect(Collectors.toList());
        stats.put("brokerBreakdown", brokerBreakdown);

        // Broker yes count
        long brokerYes = brokerBreakdown.stream()
                .filter(b -> ((String) b.get("name")).toLowerCase().contains("yes"))
                .mapToLong(b -> (Long) b.get("count"))
                .sum();
        stats.put("brokerYesCount", brokerYes);

        // ===== 3. Tender Status breakdown =====
        List<Object[]> statusData = tenderRepository.countByTenderStatus();
        List<Map<String, Object>> tenderStatusBreakdown = statusData.stream()
                .map(row -> {
                    Map<String, Object> item = new HashMap<>();
                    String val = row[0] != null ? ((String) row[0]).trim() : "Unknown";
                    // Normalize "Open/Active" and "Open / Active"
                    if (val.equalsIgnoreCase("Open/Active")) val = "Open / Active";
                    item.put("name", val);
                    item.put("count", row[1]);
                    return item;
                })
                .collect(Collectors.toList());
        // Merge duplicate "Open / Active" entries after normalization
        Map<String, Long> statusMerged = new LinkedHashMap<>();
        for (Map<String, Object> item : tenderStatusBreakdown) {
            String name = (String) item.get("name");
            long count = (Long) item.get("count");
            statusMerged.merge(name, count, Long::sum);
        }
        List<Map<String, Object>> mergedStatusBreakdown = statusMerged.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("tenderStatusBreakdown", mergedStatusBreakdown);

        // Active tenders count
        long activeTenders = statusMerged.entrySet().stream()
                .filter(e -> e.getKey().toLowerCase().contains("open") || e.getKey().toLowerCase().contains("active") || e.getKey().toLowerCase().contains("upcoming"))
                .mapToLong(Map.Entry::getValue)
                .sum();
        stats.put("activeTendersCount", activeTenders);

        // ===== 4. Insurance type breakdown =====
        List<Object[]> insuranceData = tenderRepository.countByTypeOfInsurance();
        // Parse multi-value insurance types (e.g. "GMC; GPA" -> ["GMC", "GPA"])
        Map<String, Long> insuranceMap = new LinkedHashMap<>();
        for (Object[] row : insuranceData) {
            String raw = row[0] != null ? ((String) row[0]).trim() : "Unknown";
            long count = (Long) row[1];
            // Split by semicolons and commas
            String[] parts = raw.split("[;,]");
            for (String part : parts) {
                String cleaned = part.trim();
                if (!cleaned.isEmpty()) {
                    insuranceMap.merge(cleaned, count, Long::sum);
                }
            }
        }
        List<Map<String, Object>> insuranceBreakdown = insuranceMap.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("insuranceBreakdown", insuranceBreakdown);

        // ===== 5. Existing broker breakdown =====
        List<Object[]> existingBrokerData = tenderRepository.countByExistingBroker();
        List<Map<String, Object>> existingBrokerBreakdown = existingBrokerData.stream()
                .map(row -> {
                    Map<String, Object> item = new HashMap<>();
                    String val = row[0] != null ? ((String) row[0]).trim() : "Unknown";
                    // Clean trailing commas
                    if (val.endsWith(",")) val = val.substring(0, val.length() - 1).trim();
                    item.put("name", val);
                    item.put("count", row[1]);
                    return item;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("existingBrokerBreakdown", existingBrokerBreakdown);

        // ===== 6. Opportunity stage breakdown =====
        List<Object[]> stageData = tenderRepository.countByOpportunityStage();
        Map<String, Long> stageMerged = new LinkedHashMap<>();
        for (Object[] row : stageData) {
            String val = row[0] != null ? ((String) row[0]).trim() : "Unknown";
            long count = (Long) row[1];
            // Normalize typos: "Not Intrested" -> "Not Interested"
            if (val.equalsIgnoreCase("Not Intrested")) val = "Not Interested";
            stageMerged.merge(val, count, Long::sum);
        }
        List<Map<String, Object>> opportunityStageBreakdown = stageMerged.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("opportunityStageBreakdown", opportunityStageBreakdown);

        // Meeting done count
        long meetingDone = stageMerged.entrySet().stream()
                .filter(e -> e.getKey().toLowerCase().contains("meeting done"))
                .mapToLong(Map.Entry::getValue)
                .sum();
        stats.put("meetingDoneCount", meetingDone);

        // ===== 7. Geographic distribution (NCR locations) =====
        List<Object[]> locationData = tenderRepository.countByNcrOfficeLocation();
        Map<String, Long> geoMap = new LinkedHashMap<>();
        for (Object[] row : locationData) {
            String loc = row[0] != null ? ((String) row[0]).trim().toLowerCase() : "";
            long count = (Long) row[1];
            String city;
            if (loc.contains("noida") && !loc.contains("greater noida")) {
                city = "Noida";
            } else if (loc.contains("greater noida")) {
                city = "Greater Noida";
            } else if (loc.contains("gurugram") || loc.contains("gurgaon")) {
                city = "Gurugram";
            } else if (loc.contains("faridabad")) {
                city = "Faridabad";
            } else if (loc.contains("ghaziabad")) {
                city = "Ghaziabad";
            } else if (loc.contains("delhi") || loc.contains("new delhi")) {
                city = "Delhi / New Delhi";
            } else if (loc.contains("mumbai")) {
                city = "Mumbai";
            } else if (loc.contains("kolkata")) {
                city = "Kolkata";
            } else if (loc.contains("shimla")) {
                city = "Shimla";
            } else if (loc.contains("kanpur")) {
                city = "Kanpur";
            } else if (loc.contains("chandigarh")) {
                city = "Chandigarh";
            } else {
                city = "Other";
            }
            geoMap.merge(city, count, Long::sum);
        }
        List<Map<String, Object>> geoBreakdown = geoMap.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("geoBreakdown", geoBreakdown);

        // ===== 8. Contact completeness =====
        List<Tender> allTenders = tenderRepository.findAll();
        long withPhone = allTenders.stream().filter(t -> t.getPhoneNo() != null && !t.getPhoneNo().trim().isEmpty()).count();
        long withEmail = allTenders.stream().filter(t -> t.getEmailId() != null && !t.getEmailId().trim().isEmpty()).count();
        long withBoth = allTenders.stream().filter(t ->
                (t.getPhoneNo() != null && !t.getPhoneNo().trim().isEmpty()) &&
                (t.getEmailId() != null && !t.getEmailId().trim().isEmpty())
        ).count();
        long withNeither = allTenders.stream().filter(t ->
                (t.getPhoneNo() == null || t.getPhoneNo().trim().isEmpty()) &&
                (t.getEmailId() == null || t.getEmailId().trim().isEmpty())
        ).count();
        long withTenderRef = allTenders.stream().filter(t -> t.getTenderReferenceNo() != null && !t.getTenderReferenceNo().trim().isEmpty()).count();

        List<Map<String, Object>> contactCompleteness = new ArrayList<>();
        contactCompleteness.add(Map.of("name", "Phone + Email", "count", withBoth));
        contactCompleteness.add(Map.of("name", "Phone Only", "count", withPhone - withBoth));
        contactCompleteness.add(Map.of("name", "Email Only", "count", withEmail - withBoth));
        contactCompleteness.add(Map.of("name", "Neither", "count", withNeither));
        stats.put("contactCompleteness", contactCompleteness);
        stats.put("tenderRefCount", withTenderRef);

        // ===== 9. Designation level breakdown =====
        List<Object[]> designationData = tenderRepository.countByDesignation();
        Map<String, Long> designationMap = new LinkedHashMap<>();
        for (Object[] row : designationData) {
            String desig = row[0] != null ? ((String) row[0]).trim().toLowerCase() : "unknown";
            long count = (Long) row[1];
            String level;
            if (desig.contains("chairman") || desig.contains("cmd") || desig.contains("ceo")) {
                level = "Chairman / CEO";
            } else if (desig.contains("director")) {
                level = "Director";
            } else if (desig.contains("vp") || desig.contains("vice president")) {
                level = "VP";
            } else if (desig.contains("general manager") || desig.contains("gm") || desig.contains("cgm") || desig.contains("dgm") || desig.contains("agm")) {
                level = "General Manager";
            } else if (desig.contains("chief") && (desig.contains("officer") || desig.contains("coo") || desig.contains("cfo"))) {
                level = "C-Suite / Chief Officer";
            } else if (desig.contains("manager")) {
                level = "Manager";
            } else if (desig.contains("officer") || desig.contains("sr.") || desig.contains("senior")) {
                level = "Senior Officer";
            } else if (desig.contains("head") || desig.contains("lead")) {
                level = "Head / Lead";
            } else if (desig.contains("tender") || desig.contains("procurement")) {
                level = "Tender / Procurement";
            } else if (desig.contains("hr") || desig.contains("admin") || desig.contains("finance")) {
                level = "HR / Admin / Finance";
            } else if (desig.contains("consultant") || desig.contains("advisor")) {
                level = "Consultant / Advisor";
            } else {
                level = "Other";
            }
            designationMap.merge(level, count, Long::sum);
        }
        List<Map<String, Object>> designationBreakdown = designationMap.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .collect(Collectors.toList());
        stats.put("designationBreakdown", designationBreakdown);

        // ===== 10. Broker involvement vs industry cross-reference =====
        Map<String, Long> brokerYesIndustries = new LinkedHashMap<>();
        for (Tender t : allTenders) {
            if (t.getBrokerInvolvement() != null && t.getBrokerInvolvement().trim().equalsIgnoreCase("Yes")) {
                String ind = t.getIndustryType() != null ? t.getIndustryType().trim() : "Unknown";
                brokerYesIndustries.merge(ind, 1L, Long::sum);
            }
        }
        List<Map<String, Object>> brokerIndustryBreakdown = brokerYesIndustries.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", e.getKey());
                    m.put("count", e.getValue());
                    return m;
                })
                .sorted((a, b) -> Long.compare((Long) b.get("count"), (Long) a.get("count")))
                .limit(10)
                .collect(Collectors.toList());
        stats.put("brokerIndustryBreakdown", brokerIndustryBreakdown);

        return stats;
    }
}
