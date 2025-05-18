package com.project.imdang.common.application.dto;

import lombok.Getter;

@Getter
public abstract class PagingRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
