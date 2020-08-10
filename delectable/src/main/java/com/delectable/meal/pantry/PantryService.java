package com.delectable.meal.pantry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryService extends CrudRepository<Pantry, Integer> {
    List<Pantry> findAllBydeleted(boolean deleted);
}