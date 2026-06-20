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
        tender.setEmail(tenderDetails.getEmail());
        tender.setBrokerInvolvement(tenderDetails.getBrokerInvolvement());
        tender.setRemarks(tenderDetails.getRemarks());
        tender.setAfterCallRemarks(tenderDetails.getAfterCallRemarks());

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

        // Industry breakdown
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

        // Broker involvement breakdown
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

        // Meeting status breakdown
        List<Object[]> meetingData = tenderRepository.countByAfterCallRemarks();
        long meetingDone = 0;
        long notInterested = 0;
        long others = 0;
        for (Object[] row : meetingData) {
            String remark = row[0] != null ? ((String) row[0]).trim().toLowerCase() : "";
            long count = (Long) row[1];
            if (remark.contains("meeting done") || remark.contains("done")) {
                meetingDone += count;
            } else if (remark.contains("not intrested") || remark.contains("not interested")) {
                notInterested += count;
            } else {
                others += count;
            }
        }
        List<Map<String, Object>> meetingBreakdown = new ArrayList<>();
        meetingBreakdown.add(Map.of("name", "Meeting Done", "count", meetingDone));
        meetingBreakdown.add(Map.of("name", "Not Interested", "count", notInterested));
        meetingBreakdown.add(Map.of("name", "Others/Pending", "count", others));
        stats.put("meetingBreakdown", meetingBreakdown);

        // Unique industries count
        stats.put("totalIndustries", industryBreakdown.size());

        // Broker yes count
        long brokerYes = brokerBreakdown.stream()
                .filter(b -> ((String) b.get("name")).toLowerCase().contains("yes"))
                .mapToLong(b -> (Long) b.get("count"))
                .sum();
        stats.put("brokerYesCount", brokerYes);

        return stats;
    }
}
