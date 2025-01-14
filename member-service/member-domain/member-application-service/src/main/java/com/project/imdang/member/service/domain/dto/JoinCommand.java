package com.project.imdang.member.service.domain.dto;

import com.project.imdang.member.service.domain.valueobject.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinCommand {
    //TODO : 정책
    private String nickname;
    private String birthDate;
    @Schema(description = "성별 (MALE, FEMALE 중 하나)")
    private Gender gender;
    private String deviceToken;
}
