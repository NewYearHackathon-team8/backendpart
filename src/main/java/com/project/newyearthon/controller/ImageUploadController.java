package com.project.newyearthon.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    // 업로드용 상대경로
    private static final String UPLOAD_DIR = "/Users/junseok/Desktop/project/백야/backendpart/backendpart/src/main/java/com/project/newyearthon/controller/resource/";

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") List<MultipartFile> file) {
        Map<String, String> response = new HashMap<>();

        for (MultipartFile filee : file) {
            // 파일이 비어 있는지 확인
            if (filee.isEmpty()) {
                response.put("message", "파일이 비어 있습니다.");
                response.put("fileName", filee.getOriginalFilename());
                response.put("status", ResponseEntity.status(HttpStatus.BAD_REQUEST).toString());
                continue;
            }

            try {
                // 저장 디렉토리 생성
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!uploadPath.toFile().exists()) {
                    uploadPath.toFile().mkdirs();
                }

                // 원래 파일 이름과 확장자 분리
                String originalFilename = filee.getOriginalFilename();
                String extension = ""; // 기본값 빈 문자열

                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                // 새로운 파일 이름 생성 (UUID + 확장자)
                String newFileName = UUID.randomUUID().toString() + extension;
                Path filePath = uploadPath.resolve(newFileName);

                // 파일 저장
                filee.transferTo(filePath.toFile());
                response.put("message", "파일이 성공적으로 업로드되었습니다.");
                response.put("fileName", newFileName);
                response.put("originalFileName", originalFilename);
                response.put("filePath", filePath.toString());
                response.put("status", ResponseEntity.status(200).toString());

            } catch (IOException e) {
                response.put("message", "파일 업로드 중 오류가 발생했습니다.");
                response.put("fileName", filee.getOriginalFilename());
                response.put("status", e.getMessage());
            }
        }

        return ResponseEntity.ok(response);
    }
}
