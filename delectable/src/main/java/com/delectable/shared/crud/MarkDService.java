package com.delectable.shared.crud;

public abstract class MarkDService<T extends MarkDEntity<T>> extends CRUDService<T> {

    public MarkDService(CRUDRepository<T> repository) {
        super(repository);
    }
    
    public void markAsDeleted(Long id) {
        T entity = get(id);
        entity.setDeleted(true);
        repository.save(entity);
    }
}
