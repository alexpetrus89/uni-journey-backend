package com.alex.unijourneybackend.common.domain.pagination;

import org.springframework.lang.NonNull;

public class PageQuery {

    private static final int UNPAGED_SIZE = Integer.MAX_VALUE;

    private final int page;
    private final int size;

    public PageQuery(int page, int size) {
        if (page < 0) throw new IllegalArgumentException("page must be non negative");
        if (size <= 0) throw new IllegalArgumentException("size must be positive");

        this.page = page;
        this.size = size;
    }

    @NonNull
    public static PageQuery unpaged() {
        return new PageQuery(0, UNPAGED_SIZE);
    }

    public int page() { return page; }
    public int size() { return size; }

}
