package com.example.myspringapp.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class CsrApiController {

    private final CSRPaymentRepository paymentRepo;
    private final ActivityRepository   activityRepo;

    public CsrApiController(CSRPaymentRepository paymentRepo,
                             ActivityRepository activityRepo) {
        this.paymentRepo  = paymentRepo;
        this.activityRepo = activityRepo;
    }

    // ── Stats ──────────────────────────────────────────────────────────────

    /**
     * GET /api/v1/stats
     * 메인 페이지 Impact Counter 숫자 반환
     */
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        BigDecimal totalAmount     = paymentRepo.sumTotalAmount();
        long       totalPayments   = paymentRepo.count();
        long       totalActivities = activityRepo.count();

        // 봉사자/아동 수: 별도 테이블 없을 경우 활동당 추산 기반으로 계산
        long estimatedVolunteers = totalActivities * 125L;
        long estimatedChildren   = totalPayments   * 320L;

        return ResponseEntity.ok(new StatsResponse(
                totalAmount,
                totalActivities,
                estimatedVolunteers,
                estimatedChildren
        ));
    }

    // ── Activities ─────────────────────────────────────────────────────────

    /**
     * GET /api/v1/activities?limit=3
     * 최근 활동 목록 (기본 3개)
     */
    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities(
            @RequestParam(defaultValue = "3") int limit) {
        PageRequest page = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(activityRepo.findAllByOrderByIdDesc(page));
    }

    /**
     * GET /api/v1/activities/{id}
     */
    @GetMapping("/activities/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable Long id) {
        return activityRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── Payments ───────────────────────────────────────────────────────────

    /**
     * GET /api/v1/payments
     * 기부금 집행내역 전체 (날짜 역순)
     */
    @GetMapping("/payments")
    public ResponseEntity<List<CSRPayment>> getPayments() {
        return ResponseEntity.ok(
                paymentRepo.findAll(
                        org.springframework.data.domain.Sort.by("date").descending()
                )
        );
    }

    // ── Response DTO ───────────────────────────────────────────────────────

    public record StatsResponse(
            BigDecimal totalDonation,
            long       totalActivities,
            long       volunteers,
            long       childrenSupported
    ) {}
}
