package com.project.imdang.member.application.rest;

import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.application.dto.auth.AppleLoginRequest;
import com.project.imdang.member.application.dto.auth.GoogleLoginRequest;
import com.project.imdang.member.application.dto.auth.JoinMemberRequest;
import com.project.imdang.member.application.dto.auth.KakaoLoginRequest;
import com.project.imdang.member.application.dto.auth.MockLoginRequest;
import com.project.imdang.member.application.dto.auth.ReissueTokenRequest;
import com.project.imdang.member.domain.dto.auth.JoinMemberCommand;
import com.project.imdang.member.domain.dto.auth.LoginResult;
import com.project.imdang.member.domain.dto.auth.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.auth.ReissueTokenResult;
import com.project.imdang.member.domain.dto.auth.TokenResult;
import com.project.imdang.member.domain.dto.auth.apple.AppleLoginCommand;
import com.project.imdang.member.domain.dto.auth.google.GoogleLoginCommand;
import com.project.imdang.member.domain.dto.auth.kakao.KakaoLoginCommand;
import com.project.imdang.member.domain.dto.auth.mock.MockLoginCommand;
import com.project.imdang.member.domain.ports.input.service.AuthApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "로그인 및 온보딩 API")
public class AuthController {

    private final AuthApplicationService authApplicationService;

    /**
     * 카카오 로그인
     */
    @Operation(description = "카카오 로그인 API")
    @ApiResponse(responseCode = "200", description = "카카오 로그인에 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResult.class)))
    @PostMapping("/kakao")
    public ResponseEntity<LoginResult> login(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        KakaoLoginCommand kakaoLoginCommand = KakaoLoginCommand.builder()
                .accessToken(kakaoLoginRequest.accessToken())
                .build();
        LoginResult loginResult = authApplicationService.login(kakaoLoginCommand);
        return ResponseEntity.ok(loginResult);
    }

    /**
     * 애플 로그인
     */
    @Operation(description = "애플 로그인 API")
    @ApiResponse(responseCode = "200", description = "애플 로그인에 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResult.class)))
    @PostMapping("/apple")
    public ResponseEntity<LoginResult> login(@RequestBody AppleLoginRequest appleLoginRequest) {
        AppleLoginCommand appleLoginCommand = AppleLoginCommand.builder()
                .authorizationCode(appleLoginRequest.authorizationCode())
                .build();
        LoginResult loginResult = authApplicationService.login(appleLoginCommand);
        return ResponseEntity.ok(loginResult);
    }

    /**
     * 구글 로그인
     */
    @Operation(description = "구글 로그인 API")
    @ApiResponse(responseCode = "200", description = "구글 로그인에 성공하였습니다.",
            content = @Content(schema = @Schema(implementation = LoginResult.class)))
    @PostMapping("/google")
    public ResponseEntity<LoginResult> login(@RequestBody GoogleLoginRequest googleLoginRequest) {
        GoogleLoginCommand googleLoginCommand = GoogleLoginCommand.builder()
                .accessToken(googleLoginRequest.accessToken())
                .build();
        LoginResult loginResult = authApplicationService.login(googleLoginCommand);
        return ResponseEntity.ok(loginResult);
    }

    @PostMapping("/mock")
    public ResponseEntity<LoginResult> login(@RequestBody MockLoginRequest mockLoginRequest) {
        MockLoginCommand mockLoginCommand = MockLoginCommand.builder()
                .id(mockLoginRequest.id())
                .build();
        LoginResult loginResult = authApplicationService.login(mockLoginCommand);
        return ResponseEntity.ok(loginResult);
    }

    /**
     * 회원가입
     */
    @Operation(description = "온보딩 API")
    @ApiResponse(responseCode = "200", description = "온보딩이 완료되었습니다.")
    @PutMapping("/join")
    public ResponseEntity<Void> join(@AuthenticationPrincipal UUID memberId,
                                     @RequestBody @Valid JoinMemberRequest joinMemberRequest) {
        JoinMemberCommand joinMemberCommand = JoinMemberCommand.builder()
                .memberId(new MemberId(memberId))
                .nickname(joinMemberRequest.nickname())
                .birthDate(joinMemberRequest.birthDate())
                .gender(joinMemberRequest.gender())
                .deviceToken(joinMemberRequest.deviceToken())
                .build();
        authApplicationService.join(joinMemberCommand);
        return ResponseEntity.ok().build();
    }

    /**
     * 토큰 재발급
     */
    @Operation(description = "토큰 재발급 API")
    @ApiResponse(responseCode = "200", description = "토큰 재발급 완료")
    @PostMapping("/reissue")
    public ResponseEntity<ReissueTokenResult> reissue(@RequestBody @Valid ReissueTokenRequest reissueTokenRequest) {
        ReissueTokenCommand reissueTokenCommand = ReissueTokenCommand.builder()
                .memberId(new MemberId(reissueTokenRequest.memberId()))
                .refreshToken(reissueTokenRequest.refreshToken())
                .build();
        TokenResult tokenResult = authApplicationService.reissue(reissueTokenCommand);
        ReissueTokenResult response = ReissueTokenResult.builder()
                .accessToken(tokenResult.getAccessToken())
                .refreshToken(tokenResult.getRefreshToken())
                .expiresIn(tokenResult.getExpiresIn())
                .build();
        return ResponseEntity.ok(response);
    }
}
