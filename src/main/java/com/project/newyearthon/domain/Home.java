package com.project.newyearthon.domain;

import com.project.newyearthon.domain.role.Supplier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Home { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    private String img1; // 만든 이미지 코드로 변경 예정
    private int contactPeriod;
    private int deposit;
    private int monthlyRent;
    private String address;
    private String addressDetail;
    private String roomType;
    private String roomInfo;
    private String allowance;
    private String mealInfo;
    private String sleepInfo;
    private String oneLineInfo;
    private String detailInfo;
    private boolean matched;

}
