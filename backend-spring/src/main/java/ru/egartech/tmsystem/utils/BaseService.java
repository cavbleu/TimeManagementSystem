package ru.egartech.tmsystem.utils;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T dto);
    T updateById(ID id, T dto);
    void deleteById(ID id);
}
