package com.project.imdang.setting.service.domain.dto;

import com.project.imdang.domain.dto.PagingQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListNotificationQuery extends PagingQuery {
    private Boolean checked;
}
