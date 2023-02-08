package com.aridi.springbootstarter.mapper;

public interface Mapper<F, T> {
    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
