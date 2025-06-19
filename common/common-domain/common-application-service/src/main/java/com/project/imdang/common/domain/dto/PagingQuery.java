package com.project.imdang.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PagingQuery {
    protected Integer pageNumber;
    protected Integer pageSize;
    protected String direction;
    protected String[] properties;
}
