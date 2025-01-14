package com.project.imdang.setting.service.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FcmTokenResponse {
    //TODO : Validation
    private String deviceToken;
}
