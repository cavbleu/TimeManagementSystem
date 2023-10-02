package ru.egartech.tmsystem.domain.helper;

public interface BaseMapper<T, E> {
    E toEntity(T dto);
    T toDto(E entity);
}
