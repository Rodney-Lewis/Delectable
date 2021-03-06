package com.delectable.shared.crud;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CRUSoftDeleteService<T extends CRUSoftDeleteEntity<T>> {

  /** 
  * @see com.delectable.shared.exceptions
  */
  protected final CRUSoftDeleteRepository<T> repository;

  public CRUSoftDeleteService(CRUSoftDeleteRepository<T> repository) {
    this.repository = repository;
  }

  public T create(T entity) {
    entity.setDeleted(false);
    return repository.save(entity);
  }

  public List<T> createFromList(List<T> entities) {
    return (List<T>) repository.saveAll(entities);
  }

  public T get(Long id) {
    try {
      Optional<T> entity = repository.findByIdAndDeleted(id, false);
      return entity.get();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Unknown ID for entity - " + id);
    }
  }

  public boolean isDeleted(Long id) {
    T entity = repository.findById(id).get();
    return entity.isDeleted();
  }

  public List<T> getAllByIds(List<Long> ids) {
    return (List<T>) repository.findByIdInAndDeleted(ids, false);
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
    T entity = get(id);
    entity.setDeleted(true);
    repository.save(entity);
  }
}
