package com.delectable.pantry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryService extends CrudRepository<PantryItem, Integer> {
    Page<PantryItem> findAllByDeleted(Pageable pageable, boolean deleted);

    Page<PantryItem> findAllByDeletedAndSchedulableAndNameStartingWith(Pageable pageable,
            boolean deleted, boolean schedulable, String query);

    Page<PantryItem> findAllByDeletedAndSchedulable(Pageable pageable, boolean deleted,
            boolean schedulable);
}
