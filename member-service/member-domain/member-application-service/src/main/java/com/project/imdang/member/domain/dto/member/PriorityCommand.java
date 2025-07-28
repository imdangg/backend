package com.project.imdang.member.domain.dto.member;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriorityCommand {
    private String firstPriority;
    private String secondPriority;
    private String thirdPriority;
}
