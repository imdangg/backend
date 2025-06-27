package com.project.imdang.member.application.rest;

import com.project.imdang.common.application.response.ApiResponse;
import com.project.imdang.common.domain.valueobject.MemberId;
import com.project.imdang.member.application.dto.login.LoginRequest;
import com.project.imdang.member.application.dto.login.ReissueTokenRequest;
import com.project.imdang.member.domain.dto.login.LoginCommand;
import com.project.imdang.member.domain.dto.login.LoginResult;
import com.project.imdang.member.domain.dto.login.ReissueTokenCommand;
import com.project.imdang.member.domain.dto.login.ReissueTokenResult;
import com.project.imdang.member.domain.dto.login.TokenResult;
import com.project.imdang.member.domain.ports.input.service.LoginApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.project.imdang.common.application.constant.RequestPath.LOGIN;
import static com.project.imdang.common.application.constant.RequestPath.REISSUE;

@RestController
@RequiredArgsConstructor
@Tag(name = "LoginController", description = "로그인 API")
public class LoginController {
    private final LoginApplicationService loginApplicationService;

    @Operation(description = "로그인 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공")
    })
    @PostMapping(LOGIN)
    public ApiResponse<LoginResult> login(@RequestBody LoginRequest loginRequest) {
        final LoginCommand loginCommand = LoginCommand.builder()
                .provider(loginRequest.provider())
                .identifier(loginRequest.identifier())
                .build();
        LoginResult loginResult = loginApplicationService.login(loginCommand);
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
}
