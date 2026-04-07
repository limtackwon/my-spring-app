package com.example.myspringapp.csr;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "csr_activities")
public class CsrActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate activityDate;

    @Column(nullable = false)
    private Integer participants;

    /** 모집중 | 진행중 | 완료 */
    @Column(nullable = false)
    private String status;

    /** Vuetify color name (success, primary, warning …) */
    @Column(nullable = false)
    private String statusColor;

    /** mdi-* icon name */
    @Column(nullable = false)
    private String icon;

    /** hex or Vuetify color token */
    @Column(nullable = false)
    private String iconColor;

    /** background hex */
    @Column(nullable = false)
    private String bgColor;

    /** 메인 페이지 노출 여부 */
    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getActivityDate() { return activityDate; }
    public Integer getParticipants() { return participants; }
    public String getStatus() { return status; }
    public String getStatusColor() { return statusColor; }
    public String getIcon() { return icon; }
    public String getIconColor() { return iconColor; }
    public String getBgColor() { return bgColor; }
    public Boolean getActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setActivityDate(LocalDate activityDate) { this.activityDate = activityDate; }
    public void setParticipants(Integer participants) { this.participants = participants; }
    public void setStatus(String status) { this.status = status; }
    public void setStatusColor(String statusColor) { this.statusColor = statusColor; }
    public void setIcon(String icon) { this.icon = icon; }
    public void setIconColor(String iconColor) { this.iconColor = iconColor; }
    public void setBgColor(String bgColor) { this.bgColor = bgColor; }
    public void setActive(Boolean active) { this.active = active; }
}
