package com.example.myspringapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 앱 구동 시 PostgreSQL에 샘플 데이터를 자동으로 삽입합니다.
 * 이미 데이터가 존재하면 건너뜁니다(멱등성 보장).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final CSRPaymentRepository paymentRepo;
    private final ActivityRepository   activityRepo;

    public DataSeeder(CSRPaymentRepository paymentRepo,
                      ActivityRepository activityRepo) {
        this.paymentRepo  = paymentRepo;
        this.activityRepo = activityRepo;
    }

    @Override
    public void run(String... args) {
        seedPayments();
        seedActivities();
    }

    // ── CSRPayment 샘플 10개 ───────────────────────────────────────────────

    private void seedPayments() {
        if (paymentRepo.count() > 0) {
            log.info("[Seeder] CSRPayment 데이터가 이미 존재합니다. 건너뜁니다.");
            return;
        }

        List<CSRPayment> payments = List.of(
            new CSRPayment("초록우산 어린이재단 교육 지원",
                    BigDecimal.valueOf(50_000_000), "아동교육", LocalDate.of(2025, 4, 15)),
            new CSRPayment("굿네이버스 긴급 식품 지원",
                    BigDecimal.valueOf(30_000_000), "식품지원", LocalDate.of(2025, 4, 1)),
            new CSRPayment("사랑의열매 의료비 지원",
                    BigDecimal.valueOf(20_000_000), "의료지원", LocalDate.of(2025, 3, 20)),
            new CSRPayment("지역아동센터 교육환경 개선",
                    BigDecimal.valueOf(15_000_000), "교육환경", LocalDate.of(2025, 3, 5)),
            new CSRPayment("환경재단 탄소중립 캠페인",
                    BigDecimal.valueOf(10_000_000), "환경보호", LocalDate.of(2025, 5, 1)),
            new CSRPayment("노인복지관 협회 생활 지원",
                    BigDecimal.valueOf(8_000_000),  "노인복지", LocalDate.of(2025, 5, 10)),
            new CSRPayment("장애인 자립 생활 센터 후원",
                    BigDecimal.valueOf(12_000_000), "사회복지", LocalDate.of(2025, 2, 28)),
            new CSRPayment("청소년 직업 체험 프로그램",
                    BigDecimal.valueOf(7_000_000),  "아동교육", LocalDate.of(2025, 2, 14)),
            new CSRPayment("다문화 가정 한국어 교육 지원",
                    BigDecimal.valueOf(5_000_000),  "교육환경", LocalDate.of(2025, 1, 20)),
            new CSRPayment("농어촌 의료 봉사 파견 지원",
                    BigDecimal.valueOf(9_000_000),  "의료지원", LocalDate.of(2025, 1, 10))
        );

        paymentRepo.saveAll(payments);
        log.info("[Seeder] CSRPayment 샘플 {}개 삽입 완료", payments.size());
    }

    // ── Activity 샘플 10개 ────────────────────────────────────────────────

    private void seedActivities() {
        if (activityRepo.count() > 0) {
            log.info("[Seeder] Activity 데이터가 이미 존재합니다. 건너뜁니다.");
            return;
        }

        List<Activity> activities = List.of(
            new Activity(
                "농촌 일손 돕기 봉사",
                "고령화로 어려움을 겪는 농촌 마을의 수확을 함께 도와드립니다.",
                "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?w=640",
                Map.of("tags", List.of("농촌", "봉사"), "location", "전북 익산", "participants", 48)
            ),
            new Activity(
                "아동 교육 멘토링",
                "지역 아동센터 아이들과 함께하는 학습 멘토링 프로그램입니다.",
                "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?w=640",
                Map.of("tags", List.of("교육", "아동"), "location", "서울 강북구", "participants", 23)
            ),
            new Activity(
                "한강 환경 정화 캠페인",
                "한강변 환경 정화 활동으로 깨끗한 자연을 만들어갑니다.",
                "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b?w=640",
                Map.of("tags", List.of("환경", "정화"), "location", "서울 여의도 한강공원", "participants", 67)
            ),
            new Activity(
                "독거노인 안부 돌봄",
                "홀로 계신 어르신들을 찾아 말벗이 되어드리고 생필품을 전달합니다.",
                "https://images.unsplash.com/photo-1516307365426-bea591f05011?w=640",
                Map.of("tags", List.of("노인", "돌봄"), "location", "경기 수원시", "participants", 15)
            ),
            new Activity(
                "장애인 스포츠 체험 행사",
                "장애인 분들과 함께하는 스포츠 체험 및 친목 도모 행사입니다.",
                "https://images.unsplash.com/photo-1517649763962-0c623066013b?w=640",
                Map.of("tags", List.of("장애인", "스포츠"), "location", "인천 남동구", "participants", 34)
            ),
            new Activity(
                "저소득층 주거 환경 개선",
                "낡은 주거 환경을 개선하고 도배, 방수, 보온재 설치를 지원합니다.",
                "https://images.unsplash.com/photo-1558036117-15d82a90b9b1?w=640",
                Map.of("tags", List.of("주거", "생활지원"), "location", "부산 사하구", "participants", 20)
            ),
            new Activity(
                "다문화 가정 문화 교류",
                "다양한 나라의 음식과 문화를 함께 즐기는 교류 행사입니다.",
                "https://images.unsplash.com/photo-1529156069898-49953e39b3ac?w=640",
                Map.of("tags", List.of("다문화", "교류"), "location", "서울 영등포구", "participants", 52)
            ),
            new Activity(
                "청소년 진로 탐색 캠프",
                "중·고등학생들이 다양한 직업을 체험하고 미래를 설계하는 캠프입니다.",
                "https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=640",
                Map.of("tags", List.of("청소년", "교육"), "location", "강원 춘천시", "participants", 40)
            ),
            new Activity(
                "해양 쓰레기 수거 봉사",
                "바다 환경 보호를 위한 해변 쓰레기 수거 자원봉사 활동입니다.",
                "https://images.unsplash.com/photo-1484291470158-b8f8d608850d?w=640",
                Map.of("tags", List.of("환경", "해양"), "location", "제주 협재해변", "participants", 78)
            ),
            new Activity(
                "푸드뱅크 식품 나눔 봉사",
                "잉여 식품을 분류하고 소외 계층에 전달하는 푸드뱅크 봉사입니다.",
                "https://images.unsplash.com/photo-1593113598332-cd288d649433?w=640",
                Map.of("tags", List.of("식품", "나눔"), "location", "서울 은평구", "participants", 30)
            )
        );

        activityRepo.saveAll(activities);
        log.info("[Seeder] Activity 샘플 {}개 삽입 완료", activities.size());
    }
}
