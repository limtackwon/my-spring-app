package com.example.myspringapp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CSRPaymentRepository extends JpaRepository<CSRPayment, Long> {

    /** 카테고리별 목록 */
    List<CSRPayment> findByCategoryOrderByDateDesc(String category);

    /** 메인 페이지 통계: 전체 기부금 합계 */
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM CSRPayment p")
    BigDecimal sumTotalAmount();

    /** 메인 페이지 통계: 카테고리별 집행 건수 */
    @Query("SELECT p.category, COUNT(p) FROM CSRPayment p GROUP BY p.category")
    List<Object[]> countByCategory();

    /*
     * ── pgvector 활용 예시 (벡터 유사도 검색) ──────────────────────────────
     * pgvector 확장 + embedding 컬럼 추가 후 아래 쿼리 사용 가능:
     *
     * @Query(value = """
     *     SELECT * FROM csr_payments
     *     ORDER BY embedding <-> CAST(:vec AS vector)
     *     LIMIT :k
     *     """, nativeQuery = true)
     * List<CSRPayment> findSimilar(@Param("vec") String vectorLiteral,
     *                              @Param("k")   int k);
     * ───────────────────────────────────────────────────────────────────────
     */
}
