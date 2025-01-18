package com.project.newyearthon.service;

import com.project.newyearthon.domain.Home;
import com.project.newyearthon.domain.Matching;
import com.project.newyearthon.domain.User;
import com.project.newyearthon.domain.role.Guest;
import com.project.newyearthon.dto.HomeDetailResponseDto;
import com.project.newyearthon.repository.HomeRepository;
import com.project.newyearthon.repository.MatchingRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchingService {

    private MatchingRepository matchingRepository;
    private UserRepository userRepository;
    private UserService userService;
    private HomeRepository homeRepository;

    public void createMatching(int homeId) {
        Home home = homeRepository.findById(homeId)
                .orElseThrow(() -> new IllegalArgumentException("Home not found with id: " + homeId));

        // Home의 matched 값을 true로 변경
        home.setMatched(true);
        homeRepository.save(home); // 변경사항 저장

        Matching matching = Matching.builder()
                .guest(userService.getCurrentUser().getGuest())
                .home(homeRepository.findById(homeId).orElseThrow()).build();

        matchingRepository.save(matching);
    }

    public HomeDetailResponseDto matched() {
        Guest guest = userService.getCurrentUser().getGuest();
        if (guest == null) {
            throw new IllegalStateException("Current user is not a guest.");
        }

        // 모든 Matching 리스트 조회
        List<Matching> matchings = matchingRepository.findAll();

        // Guest와 매칭된 첫 번째 Matching 검색
        Matching matching = null;
        for (Matching m : matchings) {
            if (m.getGuest().equals(guest)) {
                matching = m;
                break;
            }
        }

        // 매칭이 없는 경우 예외 처리
        if (matching == null) {
            throw new IllegalArgumentException("No matching found for the current guest.");
        }

        Home home = matching.getHome();

        // Home 정보를 HomeDetailResponseDto로 변환
        return HomeDetailResponseDto.builder()
                .img1(home.getImg1())
                .contactPeriod(home.getContactPeriod())
                .deposit(home.getDeposit())
                .monthlyRent(home.getMonthlyRent())
                .address(home.getAddress())
                .addressDetail(home.getAddressDetail())
                .roomType(home.getRoomType())
                .roomInfo(home.getRoomInfo())
                .allowance(home.getAllowance())
                .mealInfo(home.getMealInfo())
                .sleepInfo(home.getSleepInfo())
                .oneLineInfo(home.getOneLineInfo())
                .detailInfo(home.getDetailInfo())
                .build();
    }
}
