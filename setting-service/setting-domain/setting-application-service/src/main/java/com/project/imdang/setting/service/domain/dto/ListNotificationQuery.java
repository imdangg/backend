package com.project.imdang.setting.service.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListNotificationQuery {
    private UUID receiverId;
    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
