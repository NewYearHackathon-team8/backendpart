package com.project.newyearthon.service;

import com.project.newyearthon.domain.Home;
import com.project.newyearthon.domain.role.Supplier;
import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.dto.HomeResponseDto;
import com.project.newyearthon.dto.HomeSupplierResponseDto;
import com.project.newyearthon.repository.HomeRepository;
import com.project.newyearthon.repository.SupplierRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HomeService {
    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SupplierRepository supplierRepository;

    public void createHome(HomeCreateRequestDto homeCreateRequestDto) {
        Home home = Home.builder().supplier(userService.getCurrentUser().getSupplier())
                .img1(homeCreateRequestDto.getImg1())
                .contactPeriod(homeCreateRequestDto.getContactPeriod())
                .deposit(homeCreateRequestDto.getDeposit())
                .monthlyRent(homeCreateRequestDto.getMonthlyRent())
                .address(homeCreateRequestDto.getAddress())
                .addressDetail(homeCreateRequestDto.getAddressDetail())
                .roomType(homeCreateRequestDto.getRoomType())
                .roomInfo(homeCreateRequestDto.getRoomInfo())
                .allowance(homeCreateRequestDto.getAllowance())
                .mealInfo(homeCreateRequestDto.getMealInfo())
                .sleepInfo(homeCreateRequestDto.getSleepInfo())
                .oneLineInfo(homeCreateRequestDto.getOneLineInfo())
                .detailInfo(homeCreateRequestDto.getDetailInfo())
                .matched(false)
                .build();

        homeRepository.save(home);

    }

    public List<HomeResponseDto> getHomeList() {

        // 모든 Home 엔티티 가져오기
        List<Home> homes = homeRepository.findAll();

        // isMatched가 false인 경우만 필터링하여 HomeResponseDto로 변환
        return homes.stream()
                .filter(home -> !home.isMatched()) // isMatched가 false인 경우만 필터링
                .map(home -> HomeResponseDto.builder()
                        .homeId(home.getId()) // Home 엔티티의 id를 DTO의 homeId에 매핑
                        .img(home.getImg1()) // Home 엔티티의 img1을 DTO의 img에 매핑
                        .address(home.getAddress()) // 주소 매핑
                        .deposit(home.getDeposit()) // 보증금 매핑
                        .monthlyRent(home.getMonthlyRent()) // 월세 매핑
                        .oneLineInfo(home.getOneLineInfo()) // 간단 설명 매핑
                        .build())
                .collect(Collectors.toList());
    }

    public List<HomeSupplierResponseDto> getHomeSupplierList() {
        List<Home> homes = homeRepository.findAll();
        Supplier supplier = userService.getCurrentUser().getSupplier();
        return homes.stream()
                .filter(home -> home.getSupplier().equals(supplier))
                .map(home -> HomeSupplierResponseDto.builder()
                        .homeId(home.getId())
                        .address(home.getAddress())
                        .deposit(home.getDeposit())
                        .monthlyRent(home.getMonthlyRent())
                        .matched(home.isMatched())
                        .build()).collect(Collectors.toList());
    }
}
