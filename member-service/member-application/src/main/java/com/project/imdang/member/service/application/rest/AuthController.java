package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.JoinCommand;
import com.project.imdang.member.service.domain.dto.LoginResponse;
import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginCommand;
import com.project.imdang.member.service.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "로그인 및 온보딩 API")
public class AuthController {

    private final MemberApplicationService memberApplicationService;

    /**
     * 카카오 로그인
     */
    @Operation(description = "카카오 로그인 API")
    @ApiResponse(responseCode = "200", description = "카카오 로그인이 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @PostMapping("/kakao")
    public ResponseEntity<LoginResponse> login(@RequestBody KakaoLoginCommand kakaoLoginCommand) {
        LoginResponse response = memberApplicationService.login(kakaoLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 애플 로그인
     */
    @Operation(description = "애플 로그인 API")
    @ApiResponse(responseCode = "200", description = "애플 로그인이 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @PostMapping("/apple")
    public ResponseEntity<LoginResponse> login(@RequestBody AppleLoginCommand appleLoginCommand) {
        LoginResponse response = memberApplicationService.login(appleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 구글 로그인
     */
    @Operation(description = "구글 로그인 API")
    @ApiResponse(responseCode = "200", description = "구글 로그인이 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @PostMapping("/google")
    public ResponseEntity<LoginResponse> login(@RequestBody GoogleLoginCommand googleLoginCommand) {
        LoginResponse response = memberApplicationService.login(googleLoginCommand);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입
     */
    @Operation(description = "온보딩 API")
    @ApiResponse(responseCode = "200", description = "온보딩이 완료되었습니다.")
    @PutMapping("/join")
    public ResponseEntity<Void> join(@AuthenticationPrincipal UUID memberId, @RequestBody JoinCommand joinCommand) {
        memberApplicationService.join(memberId, joinCommand);
        return ResponseEntity.ok().build();
    }
}
