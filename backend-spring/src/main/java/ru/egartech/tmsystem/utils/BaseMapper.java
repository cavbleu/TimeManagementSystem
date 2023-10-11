package ru.egartech.tmsystem.utils;

public interface BaseMapper<T, E> {
    E toEntity(T dto);
    T toDto(E entity);
}
