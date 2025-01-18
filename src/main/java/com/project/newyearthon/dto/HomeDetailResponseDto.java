package com.project.newyearthon.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomeDetailResponseDto {
    private String img1;
    private String img2;
    private String img3;// 만든 이미지 코드로 변경 예정
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
