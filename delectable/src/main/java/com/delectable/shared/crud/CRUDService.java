package com.delectable.shared.crud;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CRUDService<T extends CRUDEntity<T>> {

    protected final CRUDRepository<T> repository;

    public CRUDService(CRUDRepository<T> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public T get(Long id) {
        try {
            Optional<T> entity = repository.findById(id);
            return entity.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unknown entity ID - " + id);
        }
    }

    public Page<T> getPage(Pageable pageable, String name) {
        return repository.findAllByDeletedAndNameStartingWith(pageable, false, name);
    }

    public T update(Long id, T updatedEntity) {
        T entity = get(id);
        updatedEntity.setId(entity.getId());
        return repository.save(updatedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
