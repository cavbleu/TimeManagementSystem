package ru.egartech.tmsystem.utils;

import java.util.List;

public interface BaseService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T dto);
    T updateById(ID id, T dto);
    void deleteById(ID id);
}
