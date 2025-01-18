package com.project.newyearthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeResponseDto {
    private String img;
    private String name;
    private String address;
    private int deposit;
    private int monthlyLent;
    private String oneLineInfo;
}
