package com.tender.controller;

import com.tender.model.Tender;
import com.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    // GET all tenders
    @GetMapping
    public List<Tender> getAllTenders() {
        return tenderService.getAllTenders();
    }

    // GET tender by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tender> getTenderById(@PathVariable Long id) {
        return tenderService.getTenderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create tender
    @PostMapping
    public Tender createTender(@RequestBody Tender tender) {
        return tenderService.createTender(tender);
    }

    // PUT update tender
    @PutMapping("/{id}")
    public ResponseEntity<Tender> updateTender(@PathVariable Long id, @RequestBody Tender tenderDetails) {
        try {
            Tender updated = tenderService.updateTender(id, tenderDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE tender
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTender(@PathVariable Long id) {
        try {
            tenderService.deleteTender(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET search tenders by company name
    @GetMapping("/search")
    public List<Tender> searchTenders(@RequestParam String query) {
        return tenderService.searchTenders(query);
    }

    // GET filter by industry
    @GetMapping("/filter")
    public List<Tender> filterByIndustry(@RequestParam String industry) {
        return tenderService.filterByIndustry(industry);
    }

    // GET dashboard stats
    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        return tenderService.getDashboardStats();
    }
}
