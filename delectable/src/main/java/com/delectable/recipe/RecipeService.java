package com.delectable.recipe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeService extends CrudRepository<Recipe, Integer> {
    List<Recipe> findAllByDeleted(boolean deleted);
}
