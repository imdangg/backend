package com.project.imdang.member.service.domain.dto;

import com.project.imdang.member.service.domain.valueobject.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birthDate;
    @NotNull(message = "성별을 입력해주세요.")
    private Gender gender;
}
