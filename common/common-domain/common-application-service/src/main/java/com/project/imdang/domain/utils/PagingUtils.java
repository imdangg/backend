package com.project.imdang.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PagingUtils {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String direction, String[] properties) {

        // TODO : 바인딩 수정
        if (pageNumber == null) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

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
