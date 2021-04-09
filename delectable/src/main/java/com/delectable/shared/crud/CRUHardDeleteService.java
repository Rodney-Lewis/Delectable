package com.delectable.shared.crud;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CRUHardDeleteService<T extends CRUDEntity<T>> {

    protected final CRUHardDeleteRepository<T> repository;

    public CRUHardDeleteService(CRUHardDeleteRepository<T> repository) {
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
            throw new NoSuchElementException("Unknown ID for entity - " + id);
        }
    }

    public List<T> getAllByIds(List<Long> ids) {
        return (List<T>) repository.findAllById(ids);
    }

    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
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
