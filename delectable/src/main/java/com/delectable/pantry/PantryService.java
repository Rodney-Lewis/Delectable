package com.delectable.pantry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryService extends CrudRepository<PantryItem, Integer> {
    List<PantryItem> findAllBydeleted(boolean deleted);
    List<PantryItem> findAllBydeletedAndSchedulable(boolean deleted, boolean schedulable);
}