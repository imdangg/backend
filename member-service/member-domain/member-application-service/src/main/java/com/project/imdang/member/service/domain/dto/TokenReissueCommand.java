package com.project.imdang.member.service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class TokenReissueCommand {
    @NotNull
    private UUID memberId;
    @NotBlank
    private String refreshToken;
}
