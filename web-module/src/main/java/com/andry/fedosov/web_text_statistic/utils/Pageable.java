package com.andry.fedosov.web_text_statistic.utils;

import java.util.List;

public class Pageable<T> {
    private final List<T> components;
    private final long elementsCount;

    public Pageable(List<T> components, long elementsCount) {
        this.components = components;
        this.elementsCount = elementsCount;
    }

    public List<T> getComponents() {
        return components;
    }

    public long getElementsCount() {
        return elementsCount;
    }
}
