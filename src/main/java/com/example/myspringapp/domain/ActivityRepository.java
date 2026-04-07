package com.example.myspringapp.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * 최근 N개 활동 (메인 피드용)
     * 호출: activityRepo.findAllByOrderByIdDesc(PageRequest.of(0, limit))
     */
    List<Activity> findAllByOrderByIdDesc(Pageable pageable);

    /**
     * metadata JSONB 배열(tags)에 특정 값이 포함된 활동 조회
     * @> 연산자: 좌변 jsonb가 우변 jsonb를 포함하는지 확인
     * 예) metadata->'tags' @> '["환경"]'
     */
    @Query(value = "SELECT * FROM activities WHERE metadata->'tags' @> CAST(:tagJson AS jsonb)",
           nativeQuery = true)
    List<Activity> findByTag(@Param("tagJson") String tagJson);

    /*
     * ── pgvector 활용 예시 (활동 유사도 검색) ──────────────────────────────
     * Activity 엔티티에 embedding 컬럼 추가 후 사용:
     *
     * @Query(value = """
     *     SELECT * FROM activities
     *     ORDER BY embedding <=> CAST(:vec AS vector)
     *     LIMIT :k
     *     """, nativeQuery = true)
     * List<Activity> findSimilarActivities(@Param("vec") String vectorLiteral,
     *                                      @Param("k")   int k);
     * ───────────────────────────────────────────────────────────────────────
     */
}
