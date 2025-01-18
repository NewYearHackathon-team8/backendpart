package com.project.newyearthon.controller;

import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gdg")
public class HomeController {

    private final HomeService homeService;

    @PostMapping(value = "/create-home", consumes = "multipart/form-data")
    public ResponseEntity<String> createHome(
            @ModelAttribute HomeCreateRequestDto homeCreateRequestDto) throws Exception {
        homeService.createHome(homeCreateRequestDto);
        return ResponseEntity.ok("집 정보 등록되었습니다.");
    }

    // 또는 @RequestPart를 사용하는 방식:
    @PostMapping(value = "/create-home-alt", consumes = "multipart/form-data")
    public ResponseEntity<String> createHomeAlt(
            @RequestPart("data") HomeCreateRequestDto homeCreateRequestDto,
            @RequestPart("file") List<MultipartFile> files) throws Exception {

        homeCreateRequestDto.setFile(files);
        homeService.createHome(homeCreateRequestDto);
        return ResponseEntity.ok("집 정보 등록되었습니다.");
    }
}
