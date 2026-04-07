package com.example.myspringapp.csr;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donation_records")
public class DonationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String status;

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getRecipient() { return recipient; }
    public String getCategory() { return category; }
    public Long getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(Long amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
}
