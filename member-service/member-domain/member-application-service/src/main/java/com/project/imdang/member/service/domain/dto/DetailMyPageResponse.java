package com.project.imdang.member.service.domain.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailMyPageResponse {
    private String nickname;
    private Integer insightCount;
    private Integer requestCount;
}
