package com.project.newyearthon.service;

import com.project.newyearthon.domain.Matching;
import com.project.newyearthon.domain.User;
import com.project.newyearthon.repository.MatchingRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepository matchingRepository;

    @Autowired
    private UserRepository userRepository;

    public void createMatching(Long userId, Long homeId, Long providerId, String completed, String reason) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("수요자를 찾을 수 없습니다."));

        Matching matching = new Matching();
        matching.setUser(user);
        matching.setHomeId(homeId);
        matching.setProviderId(providerId);
        matching.setCompleted(completed);
        matching.setReason(reason);

        matchingRepository.save(matching);
    }
}
