package com.project.imdang.member.service.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinCommand {
    //TODO : 정책
    private String nickname;
    private String birthDate;
    private String gender;
}
