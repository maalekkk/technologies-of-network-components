package pl.lodz.p.tks.rent.repositoriesadapters.repository;


import pl.lodz.p.tks.rent.repositoriesadapters.data.IdentityEnt;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends IdentityEnt<ID>, ID extends Serializable> extends Serializable {
    <S extends T> S save(@NotNull S entity);

    boolean existsById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    long count();

    boolean delete(@NotNull T entity);

    void deleteAll();

    boolean deleteById(ID id);
}