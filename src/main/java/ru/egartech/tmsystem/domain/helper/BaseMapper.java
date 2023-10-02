package ru.egartech.tmsystem.domain.helper;

public interface BaseMapper<D, E> {

    E toEntity(D dto);
    D toDto(E entity);

}
