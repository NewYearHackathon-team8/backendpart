package com.project.newyearthon.controller;

import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HomeService homeService;
    @PostMapping("/create-home")
    public ResponseEntity createHome(@RequestBody HomeCreateRequestDto homeCreateRequestDto) {
        homeService.createHome(homeCreateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<> getHomeList() {
        homeService.getHomeList();
    }
}
