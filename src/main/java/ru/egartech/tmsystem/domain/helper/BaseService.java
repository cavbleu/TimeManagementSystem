package ru.egartech.tmsystem.domain.helper;

import java.util.List;
import java.util.Optional;

public interface BaseService<D, ID> {

    List<D> findAll();
    Optional<D> findById(ID id);
    D save(D dto);
    D updateById(ID id, D dto);
    void deleteById(ID id);

}
