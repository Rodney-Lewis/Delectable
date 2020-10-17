package com.delectable.pantry;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryService extends CrudRepository<PantryItem, Integer> {
        List<PantryItem> findAllByDeleted(boolean deleted);

        List<PantryItem> findTop10ByDeletedAndNameStartingWith(boolean deleted, String name, Sort sort);

        List<PantryItem> findAllByDeletedAndNameStartingWith(boolean deleted, String query, Sort by);

        Page<PantryItem> findAllByDeletedAndSchedulableAndNameStartingWith(Pageable pageable,
                        boolean deleted, boolean schedulable, String query);

        Page<PantryItem> findAllByDeletedAndSchedulable(Pageable pageable, boolean deleted,
                        boolean schedulable);

}
