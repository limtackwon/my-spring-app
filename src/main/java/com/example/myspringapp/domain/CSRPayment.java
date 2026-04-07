package com.example.myspringapp.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 사회공헌(CSR) 기부금 집행 내역
 */
@Entity
@Table(name = "csr_payments")
public class CSRPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, precision = 15, scale = 0)
    private BigDecimal amount;

    /** 아동교육 | 식품지원 | 의료지원 | 환경보호 | 노인복지 */
    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false)
    private LocalDate date;

    protected CSRPayment() {}

    public CSRPayment(String title, BigDecimal amount, String category, LocalDate date) {
        this.title    = title;
        this.amount   = amount;
        this.category = category;
        this.date     = date;
    }

    public Long       getId()       { return id; }
    public String     getTitle()    { return title; }
    public BigDecimal getAmount()   { return amount; }
    public String     getCategory() { return category; }
    public LocalDate  getDate()     { return date; }
}
