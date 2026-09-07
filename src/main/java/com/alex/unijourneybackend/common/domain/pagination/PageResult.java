package com.alex.unijourneybackend.common.domain.pagination;


import java.util.List;
import java.util.function.Function;

public record PageResult<T>(@SuppressWarnings("MismatchedQueryAndUpdateOfCollection") List<T> content, long totalElements) {

    public PageResult {
        content = List.copyOf(content);
    }

    public <R> PageResult<R> map(Function<T, R> mapper) {
        return new PageResult<>(
            content.stream().map(mapper).toList(),
            totalElements
        );
    }


}
