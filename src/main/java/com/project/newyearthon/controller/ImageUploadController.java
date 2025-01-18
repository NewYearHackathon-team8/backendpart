package com.project.newyearthon.controller;

import java.util.List;
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

    private static final String UPLOAD_DIR = "/Users/junseok/Desktop/project/백야/backendpart/src/main/java/com/project/newyearthon/controller/resource/"; // 업로드된 파일을 저장할 디렉토리

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") List<MultipartFile> file) {
        Map<String, String> response = new HashMap<>();

        for (MultipartFile filee : file) {
            // 파일이 비어 있는지 확인
            if (filee.isEmpty()) {
                response.put("message", "파일이 비어 있습니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            try {
                // 저장 디렉토리 생성
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!uploadPath.toFile().exists()) {
                    uploadPath.toFile().mkdirs();
                }

                // 파일 저장 그럼
                String originalFilename = filee.getOriginalFilename();
                Path filePath = uploadPath.resolve(originalFilename);
                filee.transferTo(filePath.toFile());

                response.put("message", "파일이 성공적으로 업로드되었습니다.");
                response.put("fileName", originalFilename);
                response.put("filePath", filePath.toString());

            } catch (IOException e) {
                response.put("message", "파일 업로드 중 오류가 발생했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

        return ResponseEntity.ok(response);
    }
}

