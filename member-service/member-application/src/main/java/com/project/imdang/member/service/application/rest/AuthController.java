package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginCommand;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberApplicationService memberApplicationService;

    /**
     * 카카오 로그인
     */
    @PostMapping("/kakao")
    public ResponseEntity<LoginResponse> login(@RequestBody KakaoLoginCommand kakaoLoginCommand) {
        LoginResponse response = memberApplicationService.login(kakaoLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 애플 로그인
     */
    @PostMapping("/apple")
    public ResponseEntity<LoginResponse> login(@RequestBody AppleLoginCommand appleLoginCommand) {
        LoginResponse response = memberApplicationService.login(appleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 구글 로그인
     */
    @PostMapping("/google")
    public ResponseEntity<LoginResponse> login(@RequestBody GoogleLoginCommand googleLoginCommand) {
        LoginResponse response = memberApplicationService.login(googleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입
     */
    @PutMapping("/join")
    public ResponseEntity<Void> join(@RequestHeader(value = "Authorization") String token, @RequestBody JoinCommand joinCommand) {
        memberApplicationService.join(token.substring(7), joinCommand);
        return ResponseEntity.ok().build();
    }

    /**
     * Test
     */
    @GetMapping("/test")
    public ResponseEntity<Void> test() {
        return ResponseEntity.ok().build();
    }
}
