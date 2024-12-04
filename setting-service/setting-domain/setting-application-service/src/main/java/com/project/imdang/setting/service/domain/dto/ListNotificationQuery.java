package com.project.imdang.setting.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ListNotificationQuery {

    private Boolean checked;

    private int page;
    private int size;
    private String sort;
    private String direction;
}
