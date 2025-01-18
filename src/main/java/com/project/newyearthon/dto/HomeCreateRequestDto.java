package com.project.newyearthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeCreateRequestDto {
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
}
