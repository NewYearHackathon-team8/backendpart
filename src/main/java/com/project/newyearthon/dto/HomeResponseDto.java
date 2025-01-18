package com.project.newyearthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeResponseDto {
    private Long homeId;
    private String img;
    private String address;
    private int deposit;
    private int monthlyRent;
    private String oneLineInfo;
}
