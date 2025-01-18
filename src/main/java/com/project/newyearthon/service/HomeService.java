package com.project.newyearthon.service;

import com.project.newyearthon.domain.Home;
import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.repository.HomeRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    public void createHome(HomeCreateRequestDto homeCreateRequestDto) {
        Home home = Home.builder().user(userService.getCurrentUser())
                .img1(homeCreateRequestDto.getImg1())
                .address(homeCreateRequestDto.getAddress())
                .contactPeriod(homeCreateRequestDto.getContactPeriod())
                .deposit(homeCreateRequestDto.getDeposit())
                .monthlyRent(homeCreateRequestDto.getMonthlyRent())
                .detailInfo(homeCreateRequestDto.getDetailInfo())
                .roomInfo(homeCreateRequestDto.getRoomInfo())
                .roomType(homeCreateRequestDto.getRoomType())
                .allowance(homeCreateRequestDto.getAllowance())
                .mealInfo(homeCreateRequestDto.getMealInfo())
                .oneLineInfo(homeCreateRequestDto.getOneLineInfo())
                .sleepInfo(homeCreateRequestDto.getSleepInfo())
                .build();

        homeRepository.save(home);

    }

    public void getHomeList() {
    }
}
