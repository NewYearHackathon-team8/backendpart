package com.project.newyearthon.controller;

import com.project.newyearthon.dto.HomeDetailResponseDto;
import com.project.newyearthon.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gdg")
public class MatchingController {
    private final MatchingService matchingService;
    @PatchMapping("/match")
    public ResponseEntity<String> createMatching(@RequestParam(name = "id") int homeId) {
        matchingService.createMatching(homeId);
        return ResponseEntity.ok("Matching has been created");
    }

    @GetMapping("/matched")
    public ResponseEntity<HomeDetailResponseDto> matched() {
        return ResponseEntity.ok(matchingService.matched());
    }
}
