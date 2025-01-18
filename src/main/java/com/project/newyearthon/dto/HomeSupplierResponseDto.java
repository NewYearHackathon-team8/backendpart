package com.project.newyearthon.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HomeSupplierResponseDto {
    private Long homeId;
    private String address;
    private int deposit;
    private int monthlyRent;
    private boolean matched;
}
