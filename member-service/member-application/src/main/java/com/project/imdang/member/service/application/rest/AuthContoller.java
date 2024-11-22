package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginParamsCommand;
import com.project.imdang.member.service.domain.port.input.service.JoinService;
import com.project.imdang.member.service.domain.port.input.service.AuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthContoller {

    private final AuthLoginService authLoginService;
    private final JoinService joinService;

    /**
     * 카카오 로그인
     */
    @PostMapping("/kakao")
    public ResponseEntity<?> login(@RequestBody KakaoLoginParamsCommand kakaoLoginCommand) {
        LoginResponse response = authLoginService.login(kakaoLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 애플 로그인
     */
    @PostMapping("/apple")
    public ResponseEntity<?> login(@RequestBody AppleLoginParamsCommand appleLoginCommand) {
        LoginResponse response = authLoginService.login(appleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 구글 로그인
     */
    @PostMapping("/google")
    public ResponseEntity<?> login(@RequestBody GoogleLoginParamsCommand googleLoginCommand) {
        LoginResponse response = authLoginService.login(googleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입
     */
    @PutMapping("/join")
    public ResponseEntity<?> join(@RequestHeader(value = "Authorization") String token, @RequestBody JoinCommand joinCommand) {
        LoginResponse response = joinService.join(token.substring(7), joinCommand);
        return ResponseEntity.ok(response);
    }
}
