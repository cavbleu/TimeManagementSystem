package ru.egartech.tmsystem.domain.helper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);
    D toDTO(E entity);

}
