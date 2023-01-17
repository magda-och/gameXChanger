package com.gt.gamexchanger.mapper;

public interface DtoMapper<T, S> {
    T toDto(S domainObject);

    S toDomainObject(T dtoObject);
}


