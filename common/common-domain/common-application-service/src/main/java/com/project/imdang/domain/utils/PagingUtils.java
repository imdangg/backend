package com.project.imdang.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PagingUtils {

    public static PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String direction, String[] properties) {
        PageRequest pageRequest;
        if (properties != null && properties.length != 0) {
            Sort sort = Sort.by(Sort.Direction.valueOf(direction), properties);
            pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        } else {
            pageRequest = PageRequest.of(pageNumber, pageSize);
        }
        return pageRequest;
    }
}
