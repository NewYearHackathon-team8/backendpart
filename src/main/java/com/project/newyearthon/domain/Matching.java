package com.project.newyearthon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.tool.schema.extract.spi.ForeignKeyInformation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false) // 외래 키 매핑
    private User user; // Users 엔티티와 연관관계 설정

    private Long homeId;
    private Long providerId;
    private String completed;
    private String reason;

    // 필요 시 추가 필드 및 메서드
}
