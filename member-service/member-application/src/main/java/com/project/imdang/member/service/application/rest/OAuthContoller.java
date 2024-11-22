package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginParamsCommand;
import com.project.imdang.member.service.domain.port.input.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OAuthContoller {

    private final OAuthLoginService oAuthLoginService;

    /**
     * 카카오 로그인
     */
    @PostMapping("/kakao")
    public ResponseEntity<?> login(@RequestBody KakaoLoginParamsCommand kakaoLoginCommand) {
        LoginResponse response = oAuthLoginService.login(kakaoLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 애플 로그인
     */
    @PostMapping("/apple")
    public ResponseEntity<?> login(@RequestBody AppleLoginParamsCommand appleLoginCommand) {
        LoginResponse response = oAuthLoginService.login(appleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 구글 로그인
     */
    @PostMapping("/google")
    public ResponseEntity<?> login(@RequestBody GoogleLoginParamsCommand googleLoginCommand) {
        LoginResponse response = oAuthLoginService.login(googleLoginCommand);
        return ResponseEntity.ok(response);
    }


}
