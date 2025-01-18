package com.project.newyearthon.service;

import com.project.newyearthon.domain.Home;
import com.project.newyearthon.domain.Matching;
import com.project.newyearthon.domain.User;
import com.project.newyearthon.repository.HomeRepository;
import com.project.newyearthon.repository.MatchingRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
}
