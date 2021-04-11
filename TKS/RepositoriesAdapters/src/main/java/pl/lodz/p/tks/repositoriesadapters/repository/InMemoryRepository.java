package pl.lodz.p.tks.repositoriesadapters.repository;

import pl.lodz.p.tks.repositoriesadapters.data.IdentityEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.generator.PrimaryKeyGenerator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<T extends IdentityEnt<ID>, ID extends Serializable> implements Repository<T, ID> {
    private final PrimaryKeyGenerator<ID> generator;
    private final List<T> elements = new CopyOnWriteArrayList<>();

    public InMemoryRepository(PrimaryKeyGenerator<ID> generator) {
        this.generator = generator;
    }

    @Override
    public <S extends T> S save(@NotNull S entity) {
        if (entity.getId() == null) {
            entity.setId(generator.getId());
            elements.add(entity);
            return entity;
        }
        if (elements.contains(entity)) {
            int index = elements.indexOf(entity);
            elements.set(index, entity);
            return entity;
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean existsById(ID id) {
        return elements.stream().anyMatch(e -> e.getId().equals(id));
    }

    @Override
    public Optional<T> findById(ID id) {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Optional<T> findByUniquePredicate(Predicate<T> predicate) {
        return elements.stream().filter(predicate).findFirst();
    }

    public List<T> findByPredicate(Predicate<T> predicate) {
        return elements.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(elements);
    }

    @Override
    public long count() {
        return elements.size();
    }

    @Override
    public boolean delete(@NotNull T entity) {
        return elements.remove(entity);
    }

    @Override
    public void deleteAll() {
        elements.clear();
    }

    @Override
    public boolean deleteById(ID id) {
        return findById(id).map(elements::remove).orElse(false);
    }
}