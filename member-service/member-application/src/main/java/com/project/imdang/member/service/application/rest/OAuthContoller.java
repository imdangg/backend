package com.project.imdang.member.service.application.rest;

import com.project.imdang.member.service.domain.dto.oauth.apple.AppleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.google.GoogleLoginParamsCommand;
import com.project.imdang.member.service.domain.dto.oauth.kakao.KakaoLoginParamsCommand;
import com.project.imdang.member.service.domain.port.input.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthContoller {

    private final OAuthLoginService oAuthLoginService;

    /**
     * 카카오 로그인
     */
    @PostMapping("/kakao")
    public void login(@RequestBody KakaoLoginParamsCommand kakaoLoginCommand) {
        oAuthLoginService.login(kakaoLoginCommand);
    }

    /**
     * 애플 로그인
     */
    @PostMapping("/apple")
    public void login(@RequestBody AppleLoginParamsCommand appleLoginCommand) {
        oAuthLoginService.login(appleLoginCommand);
    }

    /**
     * 구글 로그인
     */
    @PostMapping("/google")
    public void login(@RequestBody GoogleLoginParamsCommand googleLoginCommand) {
        oAuthLoginService.login(googleLoginCommand);
    }


}
