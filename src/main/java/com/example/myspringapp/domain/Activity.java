package com.example.myspringapp.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

/**
 * 사회공헌 활동 피드
 *
 * <p>metadata 컬럼은 PostgreSQL JSONB 타입으로 저장됩니다.
 * Hibernate 6의 내장 JSON 매핑(@JdbcTypeCode + SqlTypes.JSON)을 사용하므로
 * 별도 의존성 없이 동작합니다.
 *
 * <p>pgvector 확장이 필요한 경우(예: 활동 유사도 검색):
 * <pre>
 *   // 1) pom.xml: com.pgvector:pgvector:0.1.4 추가
 *   // 2) DB: CREATE EXTENSION IF NOT EXISTS vector;
 *   // 3) 아래 필드 추가:
 *   //    @Column(columnDefinition = "vector(1536)")
 *   //    private float[] embedding;
 * </pre>
 */
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column
    private String imageUrl;

    /**
     * 태그, 위치, 담당자 등 유연한 추가 정보 – PostgreSQL JSONB로 저장
     * 예: {"tags": ["환경","봉사"], "location": "서울 한강공원"}
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    protected Activity() {}

    public Activity(String title, String description, String imageUrl, Map<String, Object> metadata) {
        this.title       = title;
        this.description = description;
        this.imageUrl    = imageUrl;
        this.metadata    = metadata;
    }

    public Long                getId()          { return id; }
    public String              getTitle()       { return title; }
    public String              getDescription() { return description; }
    public String              getImageUrl()    { return imageUrl; }
    public Map<String, Object> getMetadata()    { return metadata; }
}
