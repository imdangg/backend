package com.project.imdang.setting.domain.dto;

import com.project.imdang.common.domain.dto.PagingQuery;
import com.project.imdang.common.domain.valueobject.MemberId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ListNotificationQuery extends PagingQuery {
    private MemberId receiverId;
    private boolean isChecked;

    @Builder
    private ListNotificationQuery(Integer pageNumber, Integer pageSize, String direction, String[] properties,
                                  MemberId receiverId, boolean isChecked) {
        super(pageNumber, pageSize, direction, properties);
        this.receiverId = receiverId;
        this.isChecked = isChecked;
    }
}
