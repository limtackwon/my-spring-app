package com.example.myspringapp.csr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csr")
@CrossOrigin(origins = "http://localhost:5173")
public class CsrController {

    private final DonationRecordRepository donationRepo;
    private final VolunteerApplicationRepository volunteerRepo;

    public CsrController(DonationRecordRepository donationRepo,
                         VolunteerApplicationRepository volunteerRepo) {
        this.donationRepo = donationRepo;
        this.volunteerRepo = volunteerRepo;
    }

    /** 기부금 집행내역 목록 (최신순) */
    @GetMapping("/donations")
    public List<DonationRecord> getDonations() {
        return donationRepo.findAllByOrderByDateDesc();
    }

    /** Impact 통계 – 추후 실제 집계 쿼리로 교체 가능 */
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        long totalAmount = donationRepo.findAll().stream()
                .mapToLong(DonationRecord::getAmount)
                .sum();
        return Map.of(
                "totalDonation", totalAmount,
                "volunteers", 12500,
                "childrenSupported", 3200
        );
    }

    /** 자원봉사 신청 접수 */
    @PostMapping("/volunteer")
    public ResponseEntity<Map<String, String>> applyVolunteer(
            @RequestBody VolunteerApplication application) {
        volunteerRepo.save(application);
        return ResponseEntity.ok(Map.of("message", "신청이 완료되었습니다."));
    }
}
