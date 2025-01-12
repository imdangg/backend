package com.project.imdang.member.service.domain.dto;

import com.project.imdang.member.service.domain.valueobject.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank
    private String nickname;
    @NotBlank
    @Pattern(regexp = "^[0-9.]+$")
    private String birthDate;
    @NotNull
    private Gender gender;
}
