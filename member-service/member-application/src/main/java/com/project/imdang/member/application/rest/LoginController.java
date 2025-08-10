package com.project.imdang.member.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.application.dto.login.ReissueTokenRequest;
import com.project.imdang.member.domain.dto.login.LoginResult;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.ReissueTokenResult;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.dto.member.MemberResult;
import com.project.imdang.member.domain.handler.auth.TokenHandler;
import com.project.imdang.member.domain.ports.input.service.LoginApplicationService;
import com.project.imdang.member.domain.ports.input.service.MemberApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.project.imdang.common.application.constant.RequestPath.LOGIN;
import static com.project.imdang.common.application.constant.RequestPath.LOGOUT;
import static com.project.imdang.common.application.constant.RequestPath.REISSUE;

@RestController
@RequiredArgsConstructor
@Tag(name = "LoginController", description = "로그인 API")
public class LoginController {
    private final LoginApplicationService loginApplicationService;
    // TODO - handler로 수정
    private final MemberApplicationService memberApplicationService;
    private final TokenHandler tokenHandler;

    @Operation(description = "로그인 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공")
    })
    @PostMapping(LOGIN)
    public ApiResponse<LoginResult> login(@AuthenticationPrincipal UUID memberId) {

        // TODO - handler로 수정
        MemberResult memberResult = memberApplicationService.detailMember(new MemberId(memberId));
        boolean isJoined = memberResult.getNickname() != null;
        final String accessToken = tokenHandler.generateAccessToken(memberId);
        LoginResult loginResult = LoginResult.builder()
                .memberId(memberId)
                .isJoined(isJoined)
                .accessToken(accessToken)
                .refreshToken(memberResult.getRefreshToken())
                .build();
        return ApiResponse.success(loginResult);
    }

    @Operation(description = "토큰 재발급 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "토큰 재발급 성공")
    })
    @PostMapping(REISSUE)
    public ApiResponse<ReissueTokenResult> reissue(@RequestBody @Valid ReissueTokenRequest reissueTokenRequest) {
        ReissueTokenCommand reissueTokenCommand = ReissueTokenCommand.builder()
                .memberId(new MemberId(reissueTokenRequest.memberId()))
                .refreshToken(reissueTokenRequest.refreshToken())
                .build();
        TokenResult tokenResult = loginApplicationService.reissue(reissueTokenCommand);
        ReissueTokenResult response = ReissueTokenResult.builder()
                .accessToken(tokenResult.getAccessToken())
                .refreshToken(tokenResult.getRefreshToken())
                // TODO - CHECK
//                .expiresIn(tokenResult.getExpiresIn())
                .build();
        return ApiResponse.success(response);
    }

    @Operation(description = "로그아웃 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "로그아웃 성공")
    })
    @PostMapping(LOGOUT)
    public ApiResponse<Boolean> logout(@AuthenticationPrincipal UUID memberId) {
        Boolean logoutResult = loginApplicationService.logout(new MemberId(memberId));
        return ApiResponse.success(logoutResult);
    }
}
