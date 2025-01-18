package com.project.newyearthon.controller;

import com.project.newyearthon.dto.AccessTokenResponse;
import com.project.newyearthon.dto.RefreshTokenRequest;
import com.project.newyearthon.dto.TokenResponse;
import com.project.newyearthon.dto.UserLoginDto;
import com.project.newyearthon.dto.UserLogoutDto;
import com.project.newyearthon.dto.UserSignUpDto;
import com.project.newyearthon.jwt.TokenProvider;
import com.project.newyearthon.domain.User;
import com.project.newyearthon.service.UserService;
import com.project.newyearthon.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gdg")
public class AuthController {
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserService userService, TokenProvider tokenProvider, TokenService tokenService) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignUpDto userSignUpDto) {
        try {
            userService.registerUser(
                    userSignUpDto.getEmail(),
                    userSignUpDto.getPassword(),
                    userSignUpDto.getPhoneNumber(),
                    userSignUpDto.isPart()
            );
            return ResponseEntity.ok("회원가입이 성공적으로 처리되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.authenticateUser(
                userLoginDto.getEmail(),
                userLoginDto.getPassword()
        );
        String accessToken = tokenProvider.createAccessToken(user);
        String refreshToken = tokenService.createRefreshToken(userLoginDto.getEmail());
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody UserLogoutDto userLogoutDto) {
        tokenService.invalidateRefreshToken(userLogoutDto.getEmail());
        return ResponseEntity.ok("로그아웃됨!");
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String newAccessToken = tokenService.refreshAccessToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(new AccessTokenResponse(newAccessToken));
    }
}
