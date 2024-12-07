package com.project.imdang.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class PagingQuery {
    protected Integer pageNumber;
    protected Integer pageSize;
    protected String direction;
    protected List<String> properties;

    public String[] getProperties() {
        return this.properties.toArray(new String[0]);
    }
}
