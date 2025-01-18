package com.project.newyearthon.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeCreateRequestDto {

    private List<MultipartFile> file;
    private int contactPeriod;
    private int deposit;
    private int monthlyRent;
    private String address;
    private String roomType;
    private String roomInfo;
    private String allowance;
    private String mealInfo;
    private String sleepInfo;
    private String oneLineInfo;
    private String detailInfo;

    public void setFile(List<MultipartFile> files) {
    }
}
