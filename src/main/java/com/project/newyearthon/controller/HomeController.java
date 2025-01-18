package com.project.newyearthon.controller;

import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.dto.HomeResponseDto;
import com.project.newyearthon.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gdg")
public class HomeController {
    private final HomeService homeService;
    @PostMapping("/create-home")
    public ResponseEntity<String> createHome(@RequestBody HomeCreateRequestDto homeCreateRequestDto) {
        homeService.createHome(homeCreateRequestDto);
        return ResponseEntity.ok("집 정보 등록되었습니다.");
    }

    @GetMapping("/home-list")
    public ResponseEntity<List<HomeResponseDto>> homeList() {
        List<HomeResponseDto> homeResponseDtoList = homeService.getHomeList();
        return ResponseEntity.ok(homeResponseDtoList);
    }



}
