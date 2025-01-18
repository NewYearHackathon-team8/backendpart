package com.project.newyearthon.domain;

import com.project.newyearthon.domain.role.Guest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_id", nullable = false)
    private Home home;

    @ManyToOne(optional = true)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    // 필요 시 추가 필드 및 메서드
}
