package com.project.imdang.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class PagingQuery {
    protected Integer pageNumber = 0;
    protected Integer pageSize = 10;
    protected String direction = "ASC";
    protected List<String> properties;

    public String[] getProperties() {
        if (this.properties != null && this.properties.size() != 0) {
            return this.properties.toArray(new String[0]);
        }
        return null;
    }
}
