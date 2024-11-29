package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginCommand;
import com.project.imdang.member.service.domain.port.input.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberContoller {

    private final MemberService memberService;

    /**
     * 카카오 로그인
     */
    @PostMapping("/kakao")
    public ResponseEntity<?> login(@RequestBody KakaoLoginCommand kakaoLoginCommand) {
        LoginResponse response = memberService.login(kakaoLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 애플 로그인
     */
    @PostMapping("/apple")
    public ResponseEntity<?> login(@RequestBody AppleLoginCommand appleLoginCommand) {
        LoginResponse response = memberService.login(appleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 구글 로그인
     */
    @PostMapping("/google")
    public ResponseEntity<?> login(@RequestBody GoogleLoginCommand googleLoginCommand) {
        LoginResponse response = memberService.login(googleLoginCommand);
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
