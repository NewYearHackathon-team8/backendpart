package com.project.newyearthon.service;

import com.project.newyearthon.domain.Home;
import com.project.newyearthon.dto.HomeCreateRequestDto;
import com.project.newyearthon.repository.HomeRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class HomeService {

    private static final String UPLOAD_DIR = "/Users/junseok/Desktop/project/백야/backendpart/backendpart/src/main/java/com/project/newyearthon/controller/resource/";

    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public void createHome(HomeCreateRequestDto homeCreateRequestDto) throws Exception {
        // 방어 코드: 파일 리스트가 null이거나 비어있을 경우 처리
        if (homeCreateRequestDto.getFile() == null || homeCreateRequestDto.getFile().isEmpty()) {
            log.warn("파일이 제공되지 않았습니다.");
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }

        // 업로드된 파일의 이름을 저장할 리스트
        List<String> imgList = new ArrayList<>();

        // 파일 저장 처리
        for (MultipartFile file : homeCreateRequestDto.getFile()) {
            if (file.isEmpty()) {
                log.warn("빈 파일이 감지되었습니다: {}", file.getOriginalFilename());
                continue;
            }

            try {
                // 저장 디렉토리 생성
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                    log.info("업로드 디렉토리가 생성되었습니다: {}", uploadPath.toAbsolutePath());
                }

                // 고유 파일 이름 생성
                String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(uniqueFilename);

                // 파일 저장
                file.transferTo(filePath.toFile());
                imgList.add(uniqueFilename);
                log.info("파일이 저장되었습니다: {}", filePath.toAbsolutePath());

            } catch (IOException e) {
                log.error("파일 저장 중 오류가 발생했습니다: {}", file.getOriginalFilename(), e);
                throw new IOException("파일 저장 중 문제가 발생했습니다: " + file.getOriginalFilename(), e);
            }
        }

        // Home 엔티티 생성
        Home home = Home.builder()
                .user(userService.getCurrentUser())
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

        home.setImgList(imgList); // 저장된 이미지 리스트 설정
        homeRepository.save(home);

        log.info("Home 객체가 성공적으로 저장되었습니다: {}", home);
    }

    public void getHomeList() {
        // 향후 구현 예정
        log.info("getHomeList 호출됨");
    }
}
